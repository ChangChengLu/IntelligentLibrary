import React, {useEffect, useState} from 'react';
import {Space, Table} from 'antd';
import type { ColumnsType } from 'antd/es/table';
import {deleteUserByIdUsingPOST, listUserVoByPageUsingPOST} from "@/services/intelligent-library/userController";
import {Link, useModel, useNavigate} from "@@/exports";


/**
 * 用户管理界面
 * @constructor
 */
const UserManagementPage: React.FC = () => {

  const { initialState } = useModel('@@initialState');
  const { currentUser } = initialState ?? {};
  const navigate = useNavigate()
  const [ userVOState, setUserVOState ] = useState<API.UserVO[]>([])

  const fetchUserVO = async () => {
    const userQueryReq : API.UserQueryReq = {}
    const res = await listUserVoByPageUsingPOST(userQueryReq);
    if (res.code === 0) {
      setUserVOState(res.data.records)
    }
  }

  const handlerClick = (value: number) => {
    deleteUserByIdUsingPOST({ id: value })
    const updatedUserVOState = [
      ...userVOState.slice(0, value),
      ...userVOState.slice(value + 1)
    ];
    setUserVOState(updatedUserVOState);
  }

  const isAdmin = () => {
    if (currentUser.userRole !== 0) {
      navigate('/book/buy')
    }
  }

  useEffect(() => {
    isAdmin()
    fetchUserVO();
  }, [])

  const columns: ColumnsType<API.UserVO> = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
      render: (text) => <a>{text}</a>,
    },
    {
      title: '用户账户',
      dataIndex: 'userAccount',
      key: 'account',
    },
    {
      title: '用户昵称',
      dataIndex: 'userNickname',
      key: 'name',
    },
    {
      title: '用户性别',
      dataIndex: 'gender',
      key: 'gender',
      render: (text) => text === 0 ? '男' : '女',
    },
    {
      title: '用户邮箱',
      dataIndex: 'userEmail',
      key: 'email',
    },
    {
      title: '用户地址',
      dataIndex: 'userAddress',
      key: 'address',
    },
    {
      title: '用户描述',
      dataIndex: 'userDesc',
      key: 'userDesc',
    },
    {
      title: '操作',
      key: 'action',
      render: (_, records) => (
        <Space size="middle">
          <Link to={"/user/update"}>编辑</Link>
          <a href={'#'} onClick={() => handlerClick(records.id)}>删除</a>
          <a> { records.userRole === 0 ? '设置管理员' : '取消管理员' }</a>
        </Space>
      ),
    },
  ];



  return (
    <div className="user-management-page">
      <Table columns={columns} dataSource={userVOState} />
    </div>
  );
};
export default UserManagementPage;
