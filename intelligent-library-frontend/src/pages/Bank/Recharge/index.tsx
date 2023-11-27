/**
 * 银行账户详情页
 * @constructor
 */
import { bankRechargeUsingPOST } from "@/services/intelligent-library/bankAccountController";
import { useModel } from "@umijs/max";


import React, { useState } from "react";
import {Button, Form, Input} from "antd";
import {useNavigate} from "umi";


const BankAccountRechargePage: React.FC = () => {

  const { initialState } = useModel('@@initialState');
  const { currentUser } = initialState ?? {};
  const navigate = useNavigate()
  const [ bankRechargeReq, setBankRechargeReq ] = useState<API.BankAccountRechargeReq>({
    userId: currentUser.id,
    rechargeMoney: 0
  });
  const [ recharge, setRecharge ] = useState<string>('0')



  const handlerOnChange = (value: number) => {
    const old: API.BankAccountRechargeReq = {
      ...bankRechargeReq,
      rechargeMoney: value
    }
    setBankRechargeReq(old);
    setRecharge(value+'');
  }

  const handlerClick = async () => {
    const res = await bankRechargeUsingPOST(bankRechargeReq)
    if (res) {
      navigate("/bank/profile")
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
        <Form.Item label="充值金额">
          <Input value={recharge} onChange={(e) => handlerOnChange(parseInt(e.target.value))}/>
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
export default BankAccountRechargePage;
