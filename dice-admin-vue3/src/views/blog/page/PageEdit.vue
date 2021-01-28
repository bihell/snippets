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
    <a-input-number id="inputNumber" v-model:value="priority" :min="0" />
    <PageFooter>
      <template #right>
        <div class="components-input-demo-size">
          排序权重
          <a-input v-model:value="value" placeholder="large size" />
          <a-input v-model:value="value" placeholder="large size" />
          <a-input-number :value="99" :min="2" />
        </div>

        <a-button class="mr-2" type="dashed" @click="savePost('DRAFT')"> 保存草稿 </a-button>
        <a-button class="mr-2" disabled @click="preview"> 预览 </a-button>
        <a-button class="mr-2" type="primary" @click="savePost('PUBLISHED')"> 发布 </a-button>
        <a-button class="mr-2" disabled @click="media"> 媒体库 </a-button>
      </template>
    </PageFooter>
  </div>
</template>

<script>
  import { MarkDown } from '/@/components/Markdown';
  import { computed, onMounted } from 'vue';
  import { useRoute } from 'vue-router';
  import { PageFooter } from '/@/components/Page';
  import { store } from '../store';

  export default {
    components: { MarkDown, PageFooter },
    setup() {
      const contentHeight = computed(() => {
        return document.documentElement.clientHeight - 185;
      });

      const route = useRoute();

      const setTitle = (evt) => {
        store.setTitle(evt.target.value);
      };

      function setContent(v) {
        store.setContent(v);
      }

      function savePost(v) {
        store.savePost(v);
      }

      // todo
      function preview() {}

      // todo
      function media() {}

      onMounted(() => {
        {
          store.fetchPage(route.query.id);
        }
      });

      return {
        content: computed(() => store.state.currentPage.content),
        title: computed(() => store.state.currentPage.title),
        priority: computed(() => store.state.currentPage.priority),
        contentHeight,
        setContent,
        setTitle,
        savePost,
        preview,
        media,
      };
    },
  };
</script>

<style scoped>
  .app-container {
    padding: 6px;
  }

  .components-input-demo-size .ant-input {
    width: 200px;
    margin: 0 8px 8px 0;
  }
</style>
