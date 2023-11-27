/**
 * 银行账户详情页
 * @constructor
 */
import { getProfileByUserIdUsingPOST } from "@/services/intelligent-library/bankAccountController";
import { useModel } from "@umijs/max";
import { Form } from "antd";
import React, {useEffect, useState} from "react";



const BankAccountProfilePage: React.FC = () => {

  const { initialState } = useModel('@@initialState');
  const { currentUser } = initialState ?? {};
  const [ bankAccountVO, setBankAccountVO ] = useState<API.BankAccountVO>({})

  const fetchData = async () => {
    const res = await getProfileByUserIdUsingPOST({ id: currentUser.id });
    setBankAccountVO(res.data)
  }

  useEffect(() => {
    fetchData()
  }, [])

  // @ts-ignore
  // @ts-ignore
  return (
    <div className="bankAccount-profile-page">
      <Form
        labelCol={{ span: 4 }}
        wrapperCol={{ span: 14 }}
        layout="horizontal"
        style={{ maxWidth: 600 }}
      >
        <Form.Item label="ID" style={{ display: currentUser.userRole === 1 ? 'block' : 'none' }}>
          {bankAccountVO.id}
        </Form.Item>
        <Form.Item label="账户余额">
          {bankAccountVO.userBalance}
        </Form.Item>
        <Form.Item label="账户总消费">
          {bankAccountVO.userTotalCost}
        </Form.Item>
      </Form>
    </div>
  );
};
export default BankAccountProfilePage;
