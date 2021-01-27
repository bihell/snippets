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
      path: 'posts',
      name: 'PostList',
      component: () => import('/@/views/blog/post/PostList.vue'),
      meta: {
        title: '文章列表',
      },
    },
    {
      path: 'edit',
      name: 'PostEdit',
      component: () => import('/@/views/blog/post/PostEdit.vue'),
      meta: {
        title: '编辑文章',
      },
    },
    {
      path: 'comments',
      name: 'CommentList',
      component: () => import('/@/views/blog/comment/CommentList.vue'),
      meta: {
        title: '评论列表',
      },
    },
  ],
};

export default blog;
