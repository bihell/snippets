import type { MenuModule } from '/@/router/types.d';
import { t } from '/@/hooks/web/useI18n';

const menu: MenuModule = {
  orderNo: 0,
  menu: {
    path: '/blog',
    name: t('routes.blog.blog'),
    children: [
      {
        path: 'article',
        name: t('routes.blog.article'),
      },
    ],
  },
};
export default menu;
