import request from '@/utils/request'

export function recPlate(file) {
  return request({
    url: '/upload',
    method: 'post',
    baseURL: "/api",
    file
  })
}
