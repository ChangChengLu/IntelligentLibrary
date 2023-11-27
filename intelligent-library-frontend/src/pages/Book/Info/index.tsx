/**
 * 书籍信息详情页
 * @constructor
 */

import { Form } from "antd";
import {useModel} from "@umijs/max";
import React, {useEffect, useState} from "react";
import {getBookInfoByIdUsingPOST} from "@/services/intelligent-library/bookController";


const BookInfoPage: React.FC = () => {

  const search = window.location.search;
  const params = new URLSearchParams(search);
  const bookId = params.get('bookId') ?? 1; // 获取请求参数

  const { initialState } = useModel('@@initialState');
  const { currentUser } = initialState ?? {};
  const [ bookInfo, setBookInfo ] = useState<API.BookInfo>({})

  const fetchData = async () => {
    const res = await getBookInfoByIdUsingPOST({ id: bookId })
    setBookInfo(res.data)
  }

  useEffect(() => {
    fetchData()
  }, [])

  // @ts-ignore
  // @ts-ignore
  return (
    <div className="book-add-page">
      <Form
        labelCol={{ span: 4 }}
        wrapperCol={{ span: 14 }}
        layout="horizontal"
        style={{ maxWidth: 600 }}
      >
        <Form.Item label="ID" style={{ display: currentUser.userRole === 0 ? 'block' : 'none' }}>
          {bookInfo.id}
        </Form.Item>
        <Form.Item label="书籍名称">
          {bookInfo.bookName}
        </Form.Item>
        <Form.Item label="书籍作者">
          {bookInfo.bookAuthor}
        </Form.Item>
        <Form.Item label="书籍标签">
          {bookInfo.bookTags}
        </Form.Item>
        <Form.Item label="购买价格">
          {bookInfo.bookPrice}
        </Form.Item>
        <Form.Item label="借阅价格/每天">
          {bookInfo.bookBorrowPrice}
        </Form.Item>
        <Form.Item label="书籍库存">
          {bookInfo.bookStock}
        </Form.Item>
        <Form.Item label="书籍描述">
          {bookInfo.bookDesc}
        </Form.Item>
      </Form>
    </div>
  );
};
export default BookInfoPage;
