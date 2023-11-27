// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** getProfileByUserId POST /api/bank/profile */
export async function getProfileByUserIdUsingPOST(
  body: API.IdReq,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBankAccountVO_>('/api/bank/profile', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** bankRecharge POST /api/bank/recharge */
export async function bankRechargeUsingPOST(
  body: API.BankAccountRechargeReq,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/bank/recharge', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
