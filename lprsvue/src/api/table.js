import request from '@/utils/request'

export function getAllOrders(params) {
  return request({
    url: '/getAllOrders',
    method: 'get',
    baseURL: '/api',
    params
  })
}

export function deleteOrder(orderNo) {
  return request({
    url: `/deleteOrder?orderno=${orderNo}`,
    method: 'delete',
    baseURL: '/api'
  })
}

export function updateOrder(data) {
  return request({
    url: '/updateOrder',
    baseURL: '/api',
    method: 'post',
    data
  })
}

export function addOrder(data) {
  return request({
    url: '/addOrder',
    baseURL: '/api',
    method: 'post',
    data
  })
}

export function finishOrder(orderNo, data) {
  return request({
    url: `/finishOrder?orderno=${orderNo}`,
    method: 'post',
    baseURL: '/api',
    data
  })
}

