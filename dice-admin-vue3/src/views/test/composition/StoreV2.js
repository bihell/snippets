import { reactive } from 'vue';
import { testPosts } from './TestPosts';

class Store {
  constructor() {
    this.state = reactive({
      posts: testPosts,
      currentHashtag: null,
    });
  }

  setHashtag(tag) {
    this.state.currentHashtag = tag;
  }
}
export const store = new Store();
