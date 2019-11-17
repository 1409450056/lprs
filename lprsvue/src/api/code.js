import request from '@/utils/request'

export function resetEmail(data) {
  return request({
    url: 'api/resetEmail',
    method: 'post',
    data
  })
}

export function updatePass(pass) {
  return request({
    url: 'api/updatePass/' + pass,
    method: 'get'
  })
}
