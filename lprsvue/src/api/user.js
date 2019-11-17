import request from '@/utils/request'

export function login(username, password, code, uuid) {
  return request({
    url: 'api/login',
    baseURL: '/api',
    method: 'post',
    data: {
      username,
      password,
      code,
      uuid
    }
  })
}

export function getCodeImg() {
  return request({
    url: 'api/code',
    method: 'get',
    baseURL: '/api',
  })
}


export function register(data) {
  return request({
    url: '/api/register',
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
