import request from '@/utils/request'

export function getRoles() {
  return request({
    // url: '/roles',
    url: '/api/getUsers',
    method: 'get',
    baseURL: '/api'
  })
}

export function addRole(data) {
  return request({
    url: '/api/register',
    baseURL: '/api',
    method: 'post',
    data
  })
}

export function updateRole(id, data) {
  return request({
    url: `/api/updateUsers?id=${id}`,
    baseURL: '/api',
    method: 'put',
    data
  })
}

export function deleteRole(id) {
  return request({
    url: `/api/deleteRole?id=${id}`,
    baseURL: '/api',
    method: 'delete'
  })
}
