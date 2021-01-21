import type { App } from 'vue';
import {
  createStore,
  // createLogger, Plugin
} from 'vuex';
import { config } from 'vuex-module-decorators';
import { isDevMode } from '/@/utils/env';

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
      currentPostId: null,
    };
  },
  // change
  mutations: {
    increment(state, payload) {
      state.count += payload.number;
    },
    setPostId(state, id) {
      state.currentPostId = id;
    },
  },
});

export function setupStore(app: App<Element>) {
  app.use(store);
}

export default store;
