import Mock from 'mockjs'

const roles = [
  {
    key: 'admin',
    name: 'admin',
    description: 'Super Administrator. Have access to view all pages.'
  },
  {
    key: 'editor',
    name: 'editor',
    description: 'Normal Editor. Can see all pages except permission page'
  },
  {
    key: 'visitor',
    name: 'visitor',
    description: 'Just a visitor. Can only see the home page and the document page'
  }
]

export default [

  // mock get all roles form server
  {
    url: '/roles',
    type: 'get',
    response: _ => {
      return {
        code: 20000,
        data: roles
      }
    }
  },

  // add role
  {
    url: '/role',
    type: 'post',
    response: {
      code: 20000,
      data: {
        key: Mock.mock('@integer(300, 5000)')
      }
    }
  },

  // update role
  {
    url: '/role/[A-Za-z0-9]',
    type: 'put',
    response: {
      code: 20000,
      data: {
        status: 'success'
      }
    }
  },

  // delete role
  {
    url: '/role/[A-Za-z0-9]',
    type: 'delete',
    response: {
      code: 20000,
      data: {
        status: 'success'
      }
    }
  }
]
