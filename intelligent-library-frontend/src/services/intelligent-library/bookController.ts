// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** addBook POST /api/book/add */
export async function addBookUsingPOST(body: API.BookAddReq, options?: { [key: string]: any }) {
  return request<API.BaseResponseLong_>('/api/book/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** delBookById GET /api/book/del */
export async function delBookByIdUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.delBookByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/book/del', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** updateBook POST /api/book/update */
export async function updateBookUsingPOST(
  body: API.BookUpdateReq,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/book/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
