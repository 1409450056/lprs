import request from '@/utils/request'

export function getAllOrders(params) {
  return request({
    url: '/api/getAllOrders',
    method: 'get',
    baseURL: '/api',
    params
  })
}

export function deleteOrder(orderNo) {
  return request({
    url: `api/deleteOrder?orderno=${orderNo}`,
    method: 'delete',
    baseURL: '/api'
  })
}

export function updateOrder(data) {
  return request({
    url: '/api/updateOrder',
    baseURL: '/api',
    method: 'post',
    data
  })
}

export function addOrder(data) {
  return request({
    url: '/api/addOrder',
    baseURL: '/api',
    method: 'post',
    data
  })
}

export function finishOrder(orderNo, data) {
  return request({
    url: `api/finishOrder?orderno=${orderNo}`,
    method: 'post',
    baseURL: '/api',
    data
  })
}

export function selectByNumber(number, params) {
  return request({
    url: `api/selectOrderByNumber?number=${number}`,
    method: 'get',
    baseURL: '/api',
    params
  })
}
