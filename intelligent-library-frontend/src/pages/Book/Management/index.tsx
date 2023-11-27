/**
 * 书籍管理界面
 * @constructor
 */
import React, {useEffect, useState} from "react";
import {listBookV0ByPageUsingPOST} from "@/services/intelligent-library/bookController";
import {ColumnsType} from "antd/es/table";
import {Link, useNavigate} from "@@/exports";
import {Table} from "antd";
import initialState from "@@/plugin-initialState/@@initialState";


const BookManagementPage: React.FC = () => {

  const [ bookVOList, setBookVOList ] = useState<API.BookVO[]>([]);
  const { currentUser } = initialState ?? {};
  const navigate = useNavigate()

  const isAdmin = () => {
    if (currentUser.userRole !== 0) {
      navigate('/book/buy')
    }
  }

  const fetchInitData = async () => {
    const bookQueryReq : API.BookQueryReq = {};
    const res = await listBookV0ByPageUsingPOST(bookQueryReq);
    if (res.code === 0) {
      setBookVOList(res.data.records)
    }
  }

  useEffect(() => {
    isAdmin()
    fetchInitData();
    console.log(bookVOList)
  }, []);

  const columns: ColumnsType<API.BookVO> = [
    {
      title: 'ID',
      width: 100,
      dataIndex: 'id',
      key: 'id',
      fixed: 'left',
    },
    { title: '书籍名称', dataIndex: 'bookName', key: '1' },
    { title: '书籍作者', dataIndex: 'bookAuthor', key: '2' },
    { title: '书籍描述', dataIndex: 'bookDesc', key: '3' },
    { title: '书籍出版社', dataIndex: 'bookPublisher', key: '4' },
    { title: 'ISBN', dataIndex: 'bookIsbn', key: '5' },
    { title: '书籍标签', dataIndex: 'bookTags', key: '6' },
    { title: '书籍价格(购买)', dataIndex: 'bookPrice', key: '7' },
    { title: '书籍价格(借阅)', dataIndex: 'bookBorrowPrice', key: '8' },
    { title: '书籍库存', dataIndex: 'bookStock', key: '9' },
    {
      title: '详细',
      key: 'operation',
      fixed: 'right',
      width: 100,
      render: (text, record) => <Link to={`/book/detail?bookId=${record.id}`} target={'_blank'}>详细</Link>,
    },
    {
      title: '删除',
      key: 'operation',
      fixed: 'right',
      width: 100,
      render: () => <Link to={'/book/info'}>删除</Link>,
    },
    {
      title: '编辑',
      key: 'operation',
      fixed: 'right',
      width: 100,
      render: () => <Link to={'/book/update'}>编辑</Link>,
    },
  ];

  return (
    <div className="user-add-page">
      <Table columns={columns} dataSource={bookVOList} scroll={{ x: 1300 }} />
    </div>
  );
};
export default BookManagementPage;
