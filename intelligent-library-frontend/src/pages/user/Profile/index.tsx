/**
 * 用户信息展示界面
 * @constructor
 */
import {useModel} from "@umijs/max";
import {Form} from "antd";
import React from "react";


const UserProfilePage: React.FC = () => {

  const { initialState } = useModel('@@initialState');
  const { currentUser } = initialState ?? {};

  return (
    <div className="user-profile">
      <Form
        labelCol={{ span: 4 }}
        wrapperCol={{ span: 14 }}
        layout="horizontal"
        style={{ maxWidth: 600 }}
      >
        <Form.Item label="ID">
          {currentUser.id}
        </Form.Item>
        <Form.Item label="昵称">
          {currentUser.userNickname}
        </Form.Item>
        <Form.Item label="头像">
          {currentUser.userAvatar}
        </Form.Item>
        <Form.Item label="邮箱">
          {currentUser.userEmail}
        </Form.Item>
        <Form.Item label="地址">
          {currentUser.userAddress}
        </Form.Item>
        <Form.Item label="性别">
          { currentUser.gender === 0 ? '男' : '女' }
        </Form.Item>
        <Form.Item label="自我介绍">
          { currentUser.userDesc }
        </Form.Item>
      </Form>
    </div>
  );
};
export default UserProfilePage;
