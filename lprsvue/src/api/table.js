import request from '@/utils/request'

<<<<<<< HEAD


export function getOrders(params) {
  return request({
    url: '/api/getOrders',
    method: 'get',
    baseURL: '/api',
=======
export function getAllOrders(params) {
  return request({
    url: '/api/getAllOrders',
    method: 'get',
    baseURL: 'api',
>>>>>>> master
    params
  })
}
