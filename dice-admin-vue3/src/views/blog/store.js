import { reactive } from 'vue';
import { apiMetaList, apiSavePost, apiGetPost, apiGetPage } from '/@/api/blog/blog';
import { useMessage } from '/@/hooks/web/useMessage';
const { createMessage } = useMessage();
const { success } = createMessage;
import { formatToDateTime } from '/@/utils/dateUtil';
// import { useGo } from '/@/hooks/web/usePage';
// const go = useGo();

class Store {
  constructor() {
    this.state = reactive({
      currentPost: {},
      currentPage: {},
      tagList: [],
      categoryList: [],
    });
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
    this.state.currentPost.createTime = formatToDateTime(datetime);
  }

  setUpdateTime(datetime) {
    this.state.currentPost.updateTime = formatToDateTime(datetime);
  }

  setComment(allowComment) {
    this.state.currentPost.allowComment = allowComment;
  }

  async savePost(status) {
    this.state.currentPost.status = status;
    const postId = await apiSavePost(this.state.currentPost);
    await this.fetchPost(postId);
    // go('/blog/edit?id='+postId)
    success('保存成功');
  }

  async fetchMetaList() {
    this.state.tagList = await apiMetaList('tag');
    this.state.categoryList = await apiMetaList('category');
  }

  async fetchPost(postId) {
    if (postId) {
      this.state.currentPost = await apiGetPost(postId);
    } else {
      this.state.currentPost = {};
    }
  }

  async fetchPage(pageId) {
    if (pageId) {
      this.state.currentPage = await apiGetPage(pageId);
    } else {
      this.state.currentPage = {};
    }
  }
}
export const store = new Store();
