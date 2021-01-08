import type { MenuModule } from '/@/router/types.d';

const menu: MenuModule = {
  orderNo: 0,
  menu: {
    path: '/blog',
    name: '博客',
    children: [
      {
        path: 'list',
        name: '文章列表',
      },
    ],
  },
};
export default menu;
