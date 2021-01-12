import type { MenuModule } from '/@/router/types.d';

const menu: MenuModule = {
  orderNo: 0,
  menu: {
    path: '/test',
    name: '测试',
    children: [
      {
        path: 'test1',
        name: '测试1',
      },
    ],
  },
};
export default menu;
