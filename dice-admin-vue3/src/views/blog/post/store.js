import { reactive } from 'vue';

class Store {
  constructor() {
    this.state = reactive({
      currentPost: {},
    });
  }

  setCurrentPost(post) {
    this.state.currentPost = post;
  }

  setTitle(title) {
    this.state.currentPost.title = title;
  }

  setContent(content) {
    this.state.currentPost.content = content;
  }
}
export const store = new Store();
