/**
 * 书籍添加界面
 * @constructor
 */
import {useModel} from "@umijs/max";
import React, {useEffect, useState} from "react";
import {addBookUsingPOST} from "@/services/intelligent-library/bookController";
import {Button, Form, Input} from "antd";
import TextArea from "antd/es/input/TextArea";
import {useNavigate} from "@@/exports";


const BookAddPage: React.FC = () => {

  const [ bookAddReq, setBookAddReq ] = useState<API.BookAddReq>({})
  const { initialState } = useModel('@@initialState');
  const { currentUser } = initialState ?? {};
  const navigate = useNavigate()

  const isAdmin = () => {
    if (currentUser.userRole !== 0) {
      navigate('/book/buy')
    }
  }

  const handlerId = (value: string) => {
    const old: API.BookAddReq = {
      ...bookAddReq,
      id: parseInt(value)
    }
    setBookAddReq(old)
  }

  const handlerBookName = (value: string) => {
    const old: API.BookAddReq = {
      ...bookAddReq,
      bookName: value
    }
    setBookAddReq(old)
  }

  const handlerAuthor = (value: string) => {
    const old: API.BookAddReq = {
      ...bookAddReq,
      bookAuthor: value
    }
    setBookAddReq(old)
  }

  const handlerBookAvatar = (value: string) => {
    const old: API.BookAddReq = {
      ...bookAddReq,
      bookAvatar: value
    }
    setBookAddReq(old)
  }

  const handlerBookPublisher = (value: string) => {
    const old: API.BookAddReq = {
      ...bookAddReq,
      bookPublisher: value
    }
    setBookAddReq(old)
  }

  const handlerIsbn = (value: string) => {
    const old: API.BookAddReq = {
      ...bookAddReq,
      bookIsbn: value
    }
    setBookAddReq(old)
  }

  const handlerBookTags = (value: string) => {
    const old: API.BookAddReq = {
      ...bookAddReq,
      bookTags: value
    }
    setBookAddReq(old)
  }

  const handlerDesc = (value: string) => {
    const old: API.BookAddReq = {
      ...bookAddReq,
      bookDesc: value
    }
    setBookAddReq(old)
  }

  const handlerBookPrice = (value: string) => {
    const old: API.BookAddReq = {
      ...bookAddReq,
      bookPrice: parseFloat(value)
    }
    setBookAddReq(old)
  }

  const handlerBorrowPrice = (value: string) => {
    const old: API.BookAddReq = {
      ...bookAddReq,
      bookBorrowPrice: parseFloat(value)
    }
    setBookAddReq(old)
  }

  const handlerStock = (value: string) => {
    const old: API.BookAddReq = {
      ...bookAddReq,
      id: parseInt(value)
    }
    setBookAddReq(old)
  }

  useEffect(() => {
    isAdmin()
  }, [])

  const handlerClick = () => {
    const res = addBookUsingPOST(bookAddReq);
    if (res) {
      navigate('book/buy')
    }
  }

  return (
    <div className="book-add-page">
      <Form
        labelCol={{ span: 4 }}
        wrapperCol={{ span: 14 }}
        layout="horizontal"
        style={{ maxWidth: 600 }}
      >
        <Form.Item label="ID" style={{ display: currentUser.userRole === 1 ? 'block' : 'none' }}>
          <Input value={bookAddReq.id} onChange={(e) => handlerId(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍名称">
          <Input value={bookAddReq.bookName} onChange={(e) => handlerBookName(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍作者">
          <Input value={bookAddReq.bookAuthor} onChange={(e) => handlerAuthor(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍封面">
          <Input value={bookAddReq.bookAvatar} onChange={(e) => handlerBookAvatar(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍出版社">
          <Input value={bookAddReq.bookPublisher} onChange={(e) => handlerBookPublisher(e.target.value)}/>
        </Form.Item>
        <Form.Item label="ISBN">
          <Input value={bookAddReq.bookIsbn} onChange={(e) => handlerIsbn(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍标签">
          <Input value={bookAddReq.bookTags} onChange={(e) => handlerBookTags(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍价格(购买价格)">
          <Input value={bookAddReq.bookPrice} onChange={(e) => handlerBookPrice(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍价格(借阅价格/每天)">
          <Input value={bookAddReq.bookBorrowPrice} onChange={(e) => handlerBorrowPrice(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍库存">
          <Input value={bookAddReq.bookStock} onChange={(e) => handlerStock(e.target.value)}/>
        </Form.Item>
        <Form.Item label="书籍描述">
          <TextArea value={bookAddReq.bookDesc} onChange={(e) => handlerDesc(e.target.value)}/>
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
export default BookAddPage;
