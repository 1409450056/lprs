import request from '@/utils/request'

export function resetEmail(data) {
  return request({
    url: '/resetEmail',
    method: 'post',
    baseURL: '/api',
    data
  })
}

export function updatePass(pass) {
  return request({
    url: '/updatePass/' + pass,
    baseURL: '/api',
    method: 'get'
  })
}
