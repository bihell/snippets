import { reactive } from 'vue';
import { metaListApi, apiSavePost } from '/@/api/blog/blog';
import { useMessage } from '/@/hooks/web/useMessage';
const { createMessage } = useMessage();
const { success } = createMessage;

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

  setCreateTime(datetime) {
    this.state.currentPost.createTime = datetime;
  }

  setUpdateTime(datetime) {
    this.state.currentPost.updateTime = datetime;
  }

  setComment(allowComment) {
    this.state.currentPost.allowComment = allowComment;
  }

  async savePost(status) {
    this.state.currentPost.status = status;
    const postId = await apiSavePost(this.state.currentPost);
    console.log(postId);
    success('保存成功');
  }

  async fetchMetaList() {
    this.state.tagList = await metaListApi({ type: 'tag' });
    this.state.categoryList = await metaListApi({ type: 'category' });
  }
}
export const store = new Store();