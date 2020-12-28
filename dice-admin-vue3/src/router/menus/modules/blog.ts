import type { MenuModule } from '/@/router/types.d';

const menu: MenuModule = {
  orderNo: 0,
  menu: {
    path: '/blog',
    name: '博客',
    children: [
      {
        path: 'articles',
        name: '文章列表',
      },
      {
        path: 'article',
        name: '编辑文章',
      },
      {
        path: 'articleDrawer',
        name: '抽屉',
      },
    ],
  },
};
export default menu;
