import { reactive } from 'vue';
import { metaListApi } from '/@/api/blog/blog';

class Store {
  constructor() {
    this.state = reactive({
      currentPost: {},
      tagList: [],
      categoryList: [],
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

  setTags(tags) {
    this.state.currentPost.tags = tags.toString();
  }

  setCategory(category) {
    this.state.currentPost.category = category;
  }

  setPriority(priority) {
    priority ? (this.state.currentPost.priority = 1) : (this.state.currentPost.priority = 0);
  }

  async fetchMetaList() {
    this.state.tagList = await metaListApi({ type: 'tag' });
    this.state.categoryList = await metaListApi({ type: 'category' });
  }
}
export const store = new Store();
