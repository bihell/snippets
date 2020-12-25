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
      path: 'articles',
      name: 'articles',
      component: () => import('/@/views/blog/Articles.vue'),
      meta: {
        title: '文章列表',
      },
    },
    {
      path: 'article',
      name: 'article',
      component: () => import('/@/views/blog/Article.vue'),
      meta: {
        title: '编辑文章',
      },
    },
  ],
};

export default blog;
