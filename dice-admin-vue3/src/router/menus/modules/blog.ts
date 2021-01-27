import type { MenuModule } from '/@/router/types';

const menu: MenuModule = {
  orderNo: 0,
  menu: {
    path: '/blog',
    name: '博客',
    children: [
      {
        path: 'posts',
        name: '文章列表',
      },
      {
        path: 'comments',
        name: '评论列表',
      },
      {
        path: 'tags',
        name: '标签分类',
      },
    ],
  },
};
export default menu;
