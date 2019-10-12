import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    baseURL: '/api',
    data
  })
}

export function register(data) {
  return request({
    url: '/register',
    method: 'post',
    baseURL: '/api',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
