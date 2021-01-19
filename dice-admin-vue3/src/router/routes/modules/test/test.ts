import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';

const blog: AppRouteModule = {
  path: '/test',
  name: 'Test',
  component: LAYOUT,
  redirect: '/test/test1',
  meta: {
    icon: 'carbon:blog',
    title: '测试',
  },
  children: [
    {
      path: 'test1',
      name: 'test1',
      component: () => import('/@/views/test/test1/Test2.vue'),
      meta: {
        title: '测试',
      },
    },
    {
      path: 'Component',
      name: 'Component',
      component: () => import('/@/views/test/test1/Component.vue'),
      meta: {
        title: 'Component',
      },
    },
    {
      path: 'SignUp',
      name: 'SignUp',
      component: () => import('/@/views/test/sign-up/SignUp.vue'),
      meta: {
        title: 'Component',
      },
    },
    {
      path: 'pokemon',
      name: 'pokemon',
      component: () => import('/@/views/test/pokemon/Pokemon.vue'),
      meta: {
        title: 'pokemon',
      },
    },
    {
      path: 'pokemonsolt',
      name: 'pokemonsolt',
      component: () => import('/@/views/test/pokemon/PokemonSolt.vue'),
      meta: {
        title: 'pokemonsolt',
      },
    },
    {
      path: 'pokemonsoltnest',
      name: 'pokemonsoltnest',
      component: () => import('/@/views/test/pokemon/PokemonSoltNest.vue'),
      meta: {
        title: 'pokemonsolt套娃版',
      },
    },
    {
      path: 'composition1',
      name: 'composition1',
      component: () => import('/@/views/test/composition/1.vue'),
      meta: {
        title: 'composition1',
      },
    },
  ],
};

export default blog;
