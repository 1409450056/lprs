import request from '@/utils/request'

export function getRoles() {
  return request({
    // url: '/roles',
    url: '/getUsers',
    method: 'get',
    baseURL: '/api'
  })
}

export function addRole(data) {
  return request({
    url: '/register',
    baseURL: '/api',
    method: 'post',
    data
  })
}

export function updateRole(id, data) {
  return request({
    url: `/updateUsers?id=${id}`,
    baseURL: '/api',
    method: 'put',
    data
  })
}

export function deleteRole(id) {
  return request({
    url: `/deleteRole?id=${id}`,
    baseURL: '/api',
    method: 'delete'
  })
}
