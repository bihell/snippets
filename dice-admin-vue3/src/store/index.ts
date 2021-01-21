import type { App } from 'vue';
import {
  createStore,
  // createLogger, Plugin
} from 'vuex';
import { config } from 'vuex-module-decorators';
import { isDevMode } from '/@/utils/env';

import { testPosts } from '/@/views/test/composition/TestPosts';

const delay = () => new Promise((res) => setTimeout(res, 1000));

config.rawError = true;
const isDev = isDevMode();
// const plugins: Plugin<any>[] = isDev ? [createLogger()] : [];

const store = createStore({
  // modules: {},
  strict: isDev,
  // plugins,
  state() {
    return {
      count: 0,
      currentPost: null,
    };
  },
  // change
  mutations: {
    increment(state, payload) {
      state.count += payload.number;
    },
    setPost(state, post) {
      state.currentPost = post;
    },
  },

  // 获取数据
  actions: {
    async fetchDataFromServer(ctx, id) {
      await delay();
      const post = testPosts.find((post) => {
        return post.id === id;
      });
      // console.log(post);
      ctx.commit('setPost', post);
    },
  },
});

export function setupStore(app: App<Element>) {
  app.use(store);
}

export default store;
