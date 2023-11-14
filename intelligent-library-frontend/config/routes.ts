export default [
  { path: '/user', layout: false, routes: [{ path: '/user/login', component: './User/Login' }] },
  { path: '/user', layout: false, routes: [{ path: '/user/registry', component: './User/Registry' }] },
  { path: '/', redirect: '/add_chart' },
  { path: '/add_chart', name: '添加书籍', icon: 'barChart', component: './AddChart' },
  // { path: '/add_book', name: '数据管理', icon: 'barChart', component: './AddChart' },
  { path: '/my_chart', name: '书籍管理', icon: 'pieChart', component: './MyChart' },
  // {
  //   path: '/admin',
  //   icon: 'crown',
  //   access: 'canAdmin',
  //   routes: [
  //     { path: '/admin', name: '管理页面', redirect: '/admin/sub-page' },
  //     { path: '/admin/sub-page', name: '管理页面2', component: './Admin' },
  //   ],
  // },
  { path: '/', redirect: '/welcome' },
  { path: '*', layout: false, component: './404' },
];
