import request from '@/utils/request'

export function getAllOrders(params) {
  return request({
    url: '/api/getAllOrders',
    method: 'get',
    baseURL: 'api',
    params
  })
}
