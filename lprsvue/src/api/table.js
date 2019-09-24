import request from '@/utils/request'



export function getOrders(params) {
  return request({
    url: '/api/getOrders',
    method: 'get',
    baseURL: '/api',
    params
  })
}
