<template>
  <div class="app-container">
    <a-row :gutter="12">
      <a-col :span="24">
        <div class="mb-2">
          <a-input size="large" placeholder="请输入文章标题" :value="title" @input="setTitle" />
        </div>

        <MarkDown :value="content" :height="contentHeight" @change="setContent" />
      </a-col>
    </a-row>
    <PageFooter>
      <template #right>
        <a-button class="mr-2" type="dashed" @click="saveDraft"> 保存草稿 </a-button>
        <a-button class="mr-2" @click="preview"> 预览 </a-button>
        <a-button class="mr-2" type="primary" @click="postSetting"> 发布 </a-button>
        <a-button class="mr-2" @click="media"> 媒体库 </a-button>
      </template>
    </PageFooter>
  </div>
</template>

<script>
  import { MarkDown } from '/@/components/Markdown';
  import { computed, onMounted } from 'vue';
  import { useRoute } from 'vue-router';
  import { apiGetPost, apiSavePost } from '/@/api/blog/blog';
  import { PageFooter } from '/@/components/Page';
  import { store } from './store';
  export default {
    components: { MarkDown, PageFooter },
    setup() {
      const contentHeight = computed(() => {
        return document.documentElement.clientHeight - 185;
      });

      const route = useRoute();

      const fetchPost = async () => {
        const post = await apiGetPost(Number(route.query.id));
        store.setCurrentPost(post);
        console.log(store.state.currentPost);
      };

      const setTitle = (evt) => {
        store.setTitle(evt.target.value);
      };

      function setContent(v) {
        store.setContent(v);
      }

      function saveDraft() {
        const post = store.state.currentPost;
        post.status = 'DRAFT';
        apiSavePost(store.state.currentPost);
      }

      // todo
      function preview() {}

      // todo
      function media() {}

      function postSetting() {}

      onMounted(() => {
        {
          fetchPost();
        }
      });

      return {
        content: computed(() => store.state.currentPost.content),
        title: computed(() => store.state.currentPost.title),
        contentHeight,
        setContent,
        setTitle,
        saveDraft,
        preview,
        media,
        postSetting,
      };
    },
  };
</script>

<style scoped>
  .app-container {
    padding: 6px;
  }
</style>
