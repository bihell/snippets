import type { MenuModule } from '/@/router/types.d';
import { t } from '/@/hooks/web/useI18n';

const menu: MenuModule = {
  orderNo: 1,
  menu: {
    path: '/home/welcome',
    name: t('routes.dashboard.welcome'),
  },
};
export default menu;
