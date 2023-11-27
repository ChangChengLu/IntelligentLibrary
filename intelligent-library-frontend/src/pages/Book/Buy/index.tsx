/**
 * 书籍购买界面
 * @constructor
 */
import React, {useEffect, useState} from "react";
import {listBookInfoByPageUsingPOST} from "@/services/intelligent-library/bookController";
import {Button, Card, Form, List, message} from "antd";
import Search from "antd/es/input/Search";
import {
  bookOrderBuyUsingPOST,
  computerDiscountPriceUsingPOST,
  computerTotalPriceUsingPOST
} from "@/services/intelligent-library/orderController";
import {useModel, useNavigate} from "@@/exports";
import {Link} from "@umijs/renderer-react";

const BookBugPage: React.FC = () => {

  const initSearchParams = {
    current: 1,
    pageSize: 4,
    sortField: 'createTime',
    sortOrder: 'desc',
  };

  const { initialState } = useModel('@@initialState');
  const { currentUser } = initialState ?? {};

  const [searchParams, setSearchParams] = useState<API.BookQueryReq>({ ...initSearchParams });
  const [bookInfoList, setBookInfoList] = useState<API.BookInfo[]>();
  const [ loading, setLoading ] = useState<boolean>(true);
  const [total, setTotal] = useState<number>(0);
  const [ orderReq, setOrderReq ] = useState<API.OrderReq>({
    userId: currentUser.id,
    orderInfoList: []
  });
  const [ totalMoney, setTotalMoney ] = useState<number>(0);
  const [ discountMoney, setDiscountMoney ] = useState<number>(0);
  const navigate = useNavigate()

  const computerPrice = async () => {
    const t = await computerTotalPriceUsingPOST(orderReq);
    const d = await computerDiscountPriceUsingPOST(orderReq);
    setTotalMoney(t)
    setDiscountMoney(d)
  }

  const loadData = async () => {
    setLoading(true);
    try {
      const res = await listBookInfoByPageUsingPOST(searchParams);
      if (res.data) {
        setBookInfoList(res.data.records ?? []);
        setTotal(res.data.total ?? 0);
      } else {
        message.error('获取书籍信息失败');
      }
    } catch (e: any) {
      message.error('获取书籍信息失败，' + e.message);
    }
    setLoading(false);
  }

  const handlerClick = async () => {
    const res = await bookOrderBuyUsingPOST(orderReq)
    if (res) {
      navigate('/bank/profile')
    }
  }

  const handlerBuy = (item: API.BookInfo) => {
    const orderInfo: API.OrderInfo = {
      bookId: item.id,
      orderInfoAmount: item.bookPrice
    }
    const newOrderReq = {
      userId: orderReq.userId,
      orderInfoList: [...orderReq.orderInfoList, orderInfo]
    };
    setOrderReq(newOrderReq);
  }

  const handlerCancel = (item: API.BookInfo) => {
    const orderInfo: API.OrderInfo = {
      bookId: item.id,
      orderInfoAmount: item.bookPrice
    }
    const index = orderReq.orderInfoList?.findIndex((order) => order.id === orderInfo.id);

    if (index !== -1) {
      const newOrderInfoList = [...orderReq.orderInfoList];
      newOrderInfoList.splice(index, 1);

      const newOrderReq = {
        userId: orderReq.userId,
        orderInfoList: newOrderInfoList
      };
      setOrderReq(newOrderReq);
    }
  }

  useEffect(() => {
    loadData();
    computerPrice();
  }, [searchParams, orderReq])

  return (
    <div className="book-bug-page">
      <div>
        <Search placeholder="请输入书籍名称" enterButton loading={loading} onSearch={(value) => {
          // 设置搜索条件
          setSearchParams({
            ...initSearchParams,
            bookName: value,
          })
        }}/>
      </div>
      <div className="margin-16" />
      <List
        grid={{
          gutter: 16,
          xs: 1,
          sm: 1,
          md: 1,
          lg: 2,
          xl: 2,
          xxl: 2,
        }}
        pagination={{
          onChange: (page, pageSize) => {
            setSearchParams({
              ...searchParams,
              current: page,
              pageSize,
            })
          },
          current: searchParams.current,
          pageSize: searchParams.pageSize,
          total: total,
        }}
        loading={loading}
        dataSource={bookInfoList}
        renderItem={(item) => (
          <List.Item key={item.id}>
            <Card
              style={{ width: '100%' }}
              cover={
                <img
                  alt="example"
                  src={item.bookAvatar}
                />
              }
              actions={[
                // eslint-disable-next-line react/jsx-key
                <Button onClick={() => handlerBuy(item)}> 购买 </Button>,
                // eslint-disable-next-line react/jsx-key
                <Button onClick={() => handlerCancel(item)}> 取消 </Button>,
                // eslint-disable-next-line react/jsx-key
                <Link to={`/book/info?bookId=${item.id}`} target={'_blank'}>详细</Link>,
              ]}
            >
              <List.Item.Meta
                title={item.bookName}
                description={`价格：${item.bookPrice}`}
              />
            </Card>
          </List.Item>
        )}
      />
      <div>
        <Form>
          <Form.Item label="已购数量">
            {orderReq.orderInfoList?.length ?? 0}
          </Form.Item>
          <Form.Item label="总价格">
            {totalMoney ?? 0}
          </Form.Item>
          <Form.Item label="优惠价格">
            {discountMoney ?? 0}
          </Form.Item>
          <Form.Item label="">
            <div style={{ display: 'flex', justifyContent: 'right' }}>
              <Button type="primary" htmlType="submit" onClick={handlerClick}>
                提交
              </Button>
            </div>
          </Form.Item>
        </Form>
      </div>
    </div>
  );
};
export default BookBugPage;
