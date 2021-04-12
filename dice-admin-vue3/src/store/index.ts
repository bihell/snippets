import type { App } from 'vue';
import { createStore } from 'vuex';
import { config } from 'vuex-module-decorators';
import { isDevMode } from '/@/utils/env';
import { test } from '/@/views/test/vuex/test';
import { albums } from '/@/views/test/photos/albums';
import { photos } from '/@/views/test/photos/photos';

config.rawError = true;

const store = createStore({
  modules: { test, albums, photos },
  strict: isDevMode(),
});

export function setupStore(app: App<Element>) {
  app.use(store);
}

export default store;
