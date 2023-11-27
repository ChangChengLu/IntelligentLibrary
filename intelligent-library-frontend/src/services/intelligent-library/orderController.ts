// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** bookOrderBorrow POST /api/order/borrow */
export async function bookOrderBorrowUsingPOST(
  body: API.OrderReq,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/order/borrow', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** bookOrderBuy POST /api/order/buy */
export async function bookOrderBuyUsingPOST(body: API.OrderReq, options?: { [key: string]: any }) {
  return request<API.BaseResponseBoolean_>('/api/order/buy', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** computerDiscountPrice POST /api/order/price/discount */
export async function computerDiscountPriceUsingPOST(
  body: API.OrderReq,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBigdecimal_>('/api/order/price/discount', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** computerTotalPrice POST /api/order/price/total */
export async function computerTotalPriceUsingPOST(
  body: API.OrderReq,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBigdecimal_>('/api/order/price/total', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
