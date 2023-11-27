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

/** getBookInfoById POST /api/book/get/info */
export async function getBookInfoByIdUsingPOST(body: API.IdReq, options?: { [key: string]: any }) {
  return request<API.BaseResponseBookInfo_>('/api/book/get/info', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getBookVoById POST /api/book/get/vo */
export async function getBookVoByIdUsingPOST(body: API.IdReq, options?: { [key: string]: any }) {
  return request<API.BaseResponseBookVO_>('/api/book/get/vo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** listBookInfoByPage POST /api/book/list/page/info */
export async function listBookInfoByPageUsingPOST(
  body: API.BookQueryReq,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageBookInfo_>('/api/book/list/page/info', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** listBookV0ByPage POST /api/book/list/page/vo */
export async function listBookV0ByPageUsingPOST(
  body: API.BookQueryReq,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageBookVO_>('/api/book/list/page/vo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
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
