<template>
  <div class="p-4">
    <a-input v-model:value="titleValue" class="mb-2" placeholder="请输入标题" />
    <MarkDown ref="markDownRef" v-model:value="value" v-model:height="mdHeight" />
    <PageFooter>
      <template #right>
        <a-button class="mr-2" type="primary" @click="submitAll"> 保存草稿 </a-button>
        <a-button class="mr-2" type="primary" @click="submitAll"> 预览 </a-button>
        <a-button class="mr-2" type="danger" @click="openArticleDrawer"> 发布 </a-button>
        <a-button class="mr-2" type="primary" @click="submitAll"> 附件库 </a-button>
      </template>
    </PageFooter>

    <ArticleDrawer @register="registerArticleDrawer" />
  </div>
</template>
<script lang="ts">
  import { defineComponent, ref, unref } from 'vue';
  import { MarkDown, MarkDownActionType } from '/@/components/Markdown';
  import { PageFooter } from '/@/components/Page';
  import ArticleDrawer from './ArticleDrawer.vue';
  import { useDrawer } from '/@/components/Drawer';

  export default defineComponent({
    components: { MarkDown, PageFooter, ArticleDrawer },
    setup() {
      const markDownRef = ref<Nullable<MarkDownActionType>>(null);
      const valueRef = ref('');
      const titleValue = '';
      const [registerArticleDrawer, { openDrawer: openArticleDrawer }] = useDrawer();

      async function submitAll() {
        try {
          const markDown = unref(markDownRef);
          if (!markDown) return;
          const vditor = markDown.getVditor();
          console.log('table data:', vditor.getValue());
          console.log('table data:', valueRef);
        } catch (error) {}
      }
      const mdHeight = document.documentElement.clientHeight - 195;

      return {
        value: valueRef,
        markDownRef,
        titleValue,
        submitAll,
        mdHeight,
        registerArticleDrawer,
        openArticleDrawer,
      };
    },
  });
</script>

<style lang="less" scoped>
  .app-container {
    padding: 15px;
  }

  .mb-3 {
    margin-bottom: 1em !important;
  }

  .mb-2 {
    margin-bottom: 0.5em !important;
  }
</style>
