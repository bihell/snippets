import type { App } from 'vue';
import {
  createStore,
  // createLogger, Plugin
} from 'vuex';
import { config } from 'vuex-module-decorators';
import { isDevMode } from '/@/utils/env';
import { test } from '/@/views/test/vuex/test';
import { albums } from '/@/views/test/photos/albums';

config.rawError = true;
const isDev = isDevMode();
// const plugins: Plugin<any>[] = isDev ? [createLogger()] : [];

const store = createStore({
  modules: { test, albums },
  strict: isDev,
  // plugins,
});

export function setupStore(app: App<Element>) {
  app.use(store);
}

export default store;
