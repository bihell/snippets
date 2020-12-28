<template>
  <div class="p-4">
    <a-input v-model:value="titleValue" class="mb-2" placeholder="请输入标题" />
    <MarkDown ref="markDownRef" v-model:value="value" v-model:height="mdHeight" />
    <div class="bottom-control">
      <a-button class="mb-2" type="dashed" @click="advancedVisible = true"> 高级 </a-button>
      <a-space>
        <a-button type="dashed" @click="advancedVisible = true"> 高级 </a-button>
      </a-space>
    </div>

    <ArticleDrawer @register="registerArticleDrawer" />
  </div>
</template>
<script lang="ts">
  import { defineComponent, ref, unref } from 'vue';
  import { MarkDown, MarkDownActionType } from '/@/components/Markdown';
  import ArticleDrawer from './ArticleDrawer.vue';
  import { useDrawer } from '/@/components/Drawer';

  export default defineComponent({
    components: { MarkDown, ArticleDrawer },
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

  .bottom-control {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    padding: 7px 14px;
    text-align: right;
    background: rgb(255, 255, 255);
    border-top: 1px solid rgb(232, 232, 232);
    border-radius: 0 0 4px 4px;
    box-shadow: 0 -6px 16px -8px rgba(0, 0, 0, 0.08), 0 -9px 28px 0 rgba(0, 0, 0, 0.05),
      0 -12px 48px 16px rgba(0, 0, 0, 0.03);
  }
</style>
