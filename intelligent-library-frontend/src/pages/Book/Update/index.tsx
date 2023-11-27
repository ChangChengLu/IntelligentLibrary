/**
 * 书籍添加界面
 * @constructor
 */
import React, {useEffect, useState} from "react";
import {useModel} from "@umijs/max";
import {updateBookUsingPOST} from "@/services/intelligent-library/bookController";
import {Button, Form, Input} from "antd";
import TextArea from "antd/es/input/TextArea";
import {useNavigate} from "@@/exports";


const BookUpdatePage: React.FC = () => {

  const [ bookUpdateReq, setBookUpdateReq ] = useState<API.BookUpdateReq>({})
  const { initialState } = useModel('@@initialState');
  const { currentUser } = initialState ?? {};
  const navigate = useNavigate()

  const isAdmin = () => {
    if (currentUser.userRole !== 0) {
      navigate('/book/buy')
    }
  }

  const handlerId = (value: string) => {
    const old: API.BookUpdateReq = {
      ...bookUpdateReq,
      id: parseInt(value)
    }
    setBookUpdateReq(old)
  }

  const handlerBookName = (value: string) => {
    const old: API.BookUpdateReq = {
      ...bookUpdateReq,
      bookName: value
    }
    setBookUpdateReq(old)
  }

  const handlerAuthor = (value: string) => {
    const old: API.BookUpdateReq = {
      ...bookUpdateReq,
      bookAuthor: value
    }
    setBookUpdateReq(old)
  }

  const handlerBookAvatar = (value: string) => {
    const old: API.BookUpdateReq = {
      ...bookUpdateReq,
      bookAvatar: value
    }
    setBookUpdateReq(old)
  }

  const handlerBookPublisher = (value: string) => {
    const old: API.BookUpdateReq = {
      ...bookUpdateReq,
      bookPublisher: value
    }
    setBookUpdateReq(old)
  }

  const handlerIsbn = (value: string) => {
    const old: API.BookUpdateReq = {
      ...bookUpdateReq,
      bookIsbn: value
    }
    setBookUpdateReq(old)
  }

  const handlerBookTags = (value: string) => {
    const old: API.BookUpdateReq = {
      ...bookUpdateReq,
      bookTags: value
    }
    setBookUpdateReq(old)
  }

  const handlerDesc = (value: string) => {
    const old: API.BookUpdateReq = {
      ...bookUpdateReq,
      bookDesc: value
    }
    setBookUpdateReq(old)
  }

  const handlerBookPrice = (value: string) => {
    const old: API.BookUpdateReq = {
      ...bookUpdateReq,
      bookPrice: parseFloat(value)
    }
    setBookUpdateReq(old)
  }

  const handlerBorrowPrice = (value: string) => {
    const old: API.BookUpdateReq = {
      ...bookUpdateReq,
      bookBorrowPrice: parseFloat(value)
    }
    setBookUpdateReq(old)
  }

  const handlerStock = (value: string) => {
    const old: API.BookUpdateReq = {
      ...bookUpdateReq,
      id: parseInt(value)
    }
    setBookUpdateReq(old)
  }


  useEffect(() => {
    isAdmin()
  }, [])

  const handlerClick = async () => {
    const res = updateBookUsingPOST(bookUpdateReq);
    if (res) {
      navigate('/book/buy')
    }
  }

  return (
    <div className="book-update-page">
      <Form
        labelCol={{ span: 4 }}
        wrapperCol={{ span: 14 }}
        layout="horizontal"
        style={{ maxWidth: 600 }}
      >
        <Form.Item label="ID" style={{ display: currentUser.userRole === 1 ? 'block' : 'none' }}>
          <Input value={bookUpdateReq.id} onChange={(e) => handlerId(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍名称">
          <Input value={bookUpdateReq.bookName} onChange={(e) => handlerBookName(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍作者">
          <Input value={bookUpdateReq.bookAuthor} onChange={(e) => handlerAuthor(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍封面">
          <Input value={bookUpdateReq.bookAvatar} onChange={(e) => handlerBookAvatar(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍出版社">
          <Input value={bookUpdateReq.bookPublisher} onChange={(e) => handlerBookPublisher(e.target.value)}/>
        </Form.Item>
        <Form.Item label="ISBN">
          <Input value={bookUpdateReq.bookIsbn} onChange={(e) => handlerIsbn(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍标签">
          <Input value={bookUpdateReq.bookTags} onChange={(e) => handlerBookTags(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍价格(购买价格)">
          <Input value={bookUpdateReq.bookPrice} onChange={(e) => handlerBookPrice(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍价格(借阅价格/每天)">
          <Input value={bookUpdateReq.bookBorrowPrice} onChange={(e) => handlerBorrowPrice(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍库存">
          <Input value={bookUpdateReq.bookStock} onChange={(e) => handlerStock(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍描述">
          <TextArea value={bookUpdateReq.bookDesc} onChange={(e) => handlerDesc(e.target.value)}/>
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
  );
};
export default BookUpdatePage;
