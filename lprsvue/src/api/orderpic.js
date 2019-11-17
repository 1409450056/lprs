import request from '@/utils/request'

export function selectAllByMarkOrder(params) {
  return request({
    url: '/api/selectAllByMarkOrder',
    method: 'get',
    baseURL: '/api',
    params
  })
}

export function selectByMark(params) {
  return request({
    url: `api/selectByMark?mark=1`,
    method: 'get',
    baseURL: '/api',
    params
  })
}

export function updateAllByUrl(number) {
  return request({
    url:  `api/updateAllByUrl?number=${number}`,
    method: 'post',
    baseURL: '/api',
  })
}