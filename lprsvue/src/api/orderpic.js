import request from '@/utils/request'

export function selectAllByMarkOrder(params) {
  return request({
    url: '/api/selectAllByMarkOrder',
    method: 'get',
    baseURL: '/api',
    params
  })
}