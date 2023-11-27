/**
 * 用户更新信息界面
 * @constructor
 */
import { updateUserUsingPOST } from "@/services/intelligent-library/userController";
import { useModel } from "@umijs/max";

import {Button, Form, Input, Radio} from "antd";
import React, {useEffect, useState} from "react";
import TextArea from "antd/es/input/TextArea";
import {useNavigate} from "@@/exports";



const UserUpdatePage: React.FC = () => {

  const { initialState } = useModel('@@initialState');
  const { currentUser } = initialState ?? {};
  const [ userUpdateReq, setUserUpdateReq ] = useState<API.UserUpdateReq>({
    id: currentUser.id,
    userNickname: currentUser.userNickname,
    userDesc: currentUser.userDesc,
    userEmail: currentUser.userEmail,
    userAvatar: currentUser.userAvatar,
    userRole: currentUser.userRole,
    userAddress: currentUser.userAddress,
  })
  const navigate = useNavigate()

  const handlerClick = async () => {
    const res = await updateUserUsingPOST(userUpdateReq);
    if (res) {
      navigate("/book/buy")
    }
  }

  const handlerId = (value: string) => {
    const old: API.UserUpdateReq = {
      ...currentUser,
      id: parseInt(value)
    }
    setUserUpdateReq(old)
  }

  const handlerAvatar = (value: string) => {
    const old: API.UserUpdateReq = {
      ...userUpdateReq,
      userAvatar: value
    }
    setUserUpdateReq(old)
  }

  const handlerEmail = (value: string) => {
    const old: API.UserUpdateReq = {
      ...userUpdateReq,
      userEmail: value
    }
    setUserUpdateReq(old)
  }

  const handlerPhone = (value: string) => {
    const old: API.UserUpdateReq = {
      ...userUpdateReq,
      userPhone: value
    }
    setUserUpdateReq(old)
  }

  const handlerAddress = (value: string) => {
    const old: API.UserUpdateReq = {
      ...userUpdateReq,
      userAddress: value
    }
    setUserUpdateReq(old)
  }

  const handlerNickName = (value: string) => {
    const old: API.UserUpdateReq = {
      ...userUpdateReq,
      userNickname: value
    }
    setUserUpdateReq(old)
  }

  const handlerUserDesc = (value: string) => {
    const old: API.UserUpdateReq = {
      ...userUpdateReq,
      userDesc: value
    }
    setUserUpdateReq(old)
  }

  return (
    <div className="user-update-page">
      <Form
        labelCol={{ span: 4 }}
        wrapperCol={{ span: 14 }}
        layout="horizontal"
        style={{ maxWidth: 600 }}
      >
        <Form.Item label="ID" style={{ display: currentUser.userRole === 0 ? 'block' : 'none' }}>
          <Input value={userUpdateReq.id} onChange={(e) => handlerId(e.target.value)} />
        </Form.Item>
        <Form.Item label="昵称">
          <Input value={userUpdateReq.userNickname} onChange={(e) => handlerNickName(e.target.value)}/>
        </Form.Item>
        <Form.Item label="头像">
          <Input value={userUpdateReq.userAvatar} onChange={(e) => handlerAvatar(e.target.value)}/>
        </Form.Item>
        <Form.Item label="邮箱">
          <Input value={userUpdateReq.userEmail} onChange={(e) => handlerEmail(e.target.value)}/>
        </Form.Item>
        <Form.Item label="电话">
          <Input value={userUpdateReq.userPhone} onChange={(e) => handlerPhone(e.target.value)}/>
        </Form.Item>
        <Form.Item label="地址">
          <TextArea value={userUpdateReq.userAddress} onChange={(e) => handlerAddress(e.target.value)}/>
        </Form.Item>
        <Form.Item label="性别">
          <Radio.Group value={userUpdateReq.gender}>
            <Radio value="0"> 男 </Radio>
            <Radio value="1"> 女 </Radio>
          </Radio.Group>
        </Form.Item>
        <Form.Item label="自我介绍">
          <TextArea value={userUpdateReq.userDesc} onChange={(e) => handlerUserDesc(e.target.value)}/>
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
export default UserUpdatePage;
