import type { MenuModule } from '/@/router/types';

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
      {
        path: 'Component',
        name: 'Component',
      },
      {
        path: 'SignUp',
        name: 'SignUp',
      },
      {
        path: 'pokemon',
        name: 'pokemon',
      },
      {
        path: 'pokemonsolt',
        name: 'pokemonsolt',
      },
      {
        path: 'pokemonsoltnest',
        name: 'pokemonsolt套娃版',
      },
      {
        path: 'composition1',
        name: 'composition1',
      },
    ],
  },
};
export default menu;
