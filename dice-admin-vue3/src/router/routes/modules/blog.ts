import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';
import { t } from '/@/hooks/web/useI18n';

const tree: AppRouteModule = {
  path: '/blog',
  name: 'Blog',
  component: LAYOUT,
  redirect: '/blog/article',
  meta: {
    icon: 'carbon:blog',
    title: t('routes.blog.blog'),
  },
  children: [
    {
      path: 'article',
      name: 'article',
      component: () => import('/@/views/demo/tree/index.vue'),
      meta: {
        title: t('routes.blog.article'),
      },
    },
  ],
};

export default tree;
