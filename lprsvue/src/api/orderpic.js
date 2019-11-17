import request from '@/utils/request'

export function selectAllByMarkOrder(params) {
  return request({
    url: '/selectAllByMarkOrder',
    method: 'get',
    baseURL: '/api',
    params
  })
}

export function selectByMark(params) {
  return request({
    url: `/selectByMark?mark=1`,
    method: 'get',
    baseURL: '/api',
    params
  })
}

export function updateAllByUrl(number) {
  return request({
    url:  `/updateAllByUrl?number=${number}`,
    method: 'post',
    baseURL: '/api',
  })
}