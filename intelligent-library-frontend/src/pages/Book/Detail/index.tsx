/**
 * 书籍信息详情页
 * @constructor
 */

import { Form } from "antd";
import {useModel} from "@umijs/max";
import React, {useEffect, useState} from "react";
import {getBookVoByIdUsingPOST} from "@/services/intelligent-library/bookController";


const BookDetailPage: React.FC = () => {

  const search = window.location.search;
  const params = new URLSearchParams(search);
  const bookId = params.get('bookId') ?? 1; // 获取请求参数

  const [ bookVO, setBookVO ] = useState<API.BookVO>({});
  const { initialState } = useModel('@@initialState');
  const { currentUser } = initialState ?? {};

  const fetchData = async () => {
    const res = await getBookVoByIdUsingPOST({ id: bookId })
    setBookVO(res.data)
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
          {bookVO.id}
        </Form.Item>
        <Form.Item label="书籍名称">
          {bookVO.bookName}
        </Form.Item>
        <Form.Item label="书籍作者">
          {bookVO.bookAuthor}
        </Form.Item>
        <Form.Item label="书籍封面">
          {bookVO.bookAvatar}
        </Form.Item>
        <Form.Item label="书籍出版社">
          {bookVO.bookPublisher}
        </Form.Item>
        <Form.Item label="ISBN">
          {bookVO.bookIsbn}
        </Form.Item>
        <Form.Item label="书籍标签">
          {bookVO.bookTags}
        </Form.Item>
        <Form.Item label="购买价格">
          {bookVO.bookPrice}
        </Form.Item>
        <Form.Item label="借阅价格/每天">
          {bookVO.bookBorrowPrice}
        </Form.Item>
        <Form.Item label="书籍库存">
          {bookVO.bookStock}
        </Form.Item>
        <Form.Item label="书籍描述">
          {bookVO.bookDesc}
        </Form.Item>
      </Form>
    </div>
  );
};
export default BookDetailPage;
