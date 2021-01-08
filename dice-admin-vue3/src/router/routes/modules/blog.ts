import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';

const blog: AppRouteModule = {
  path: '/blog',
  name: 'Blog',
  component: LAYOUT,
  redirect: '/blog/article',
  meta: {
    icon: 'carbon:blog',
    title: '博客',
  },
  children: [
    {
      path: 'list',
      name: 'PostList',
      component: () => import('/@/views/blog/PostList.vue'),
      meta: {
        title: '文章列表',
      },
    },
    {
      path: 'edit',
      name: 'PostEdit',
      component: () => import('/@/views/blog/PostEdit.vue'),
      meta: {
        title: '编辑文章',
      },
    },
  ],
};

export default blog;
