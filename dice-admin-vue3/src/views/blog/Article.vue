<template>
  <div class="p-4">
    <a-input v-model:value="titleValue" class="mr-2" placeholder="请输入标题" />
    <MarkDown ref="markDownRef" v-model:value="value" v-model:height="mdHeight" />
    <PageFooter>
      <template #right>
        <a-button class="mr-2" type="dashed" @click="submitAll"> 保存草稿 </a-button>
        <a-button class="mr-2" @click="openDrawer1(true)"> 预览 </a-button>
        <a-button class="mr-2" type="primary" @click="openDrawer1(true)"> 发布 </a-button>
        <a-button class="mr-2" @click="openDrawer1(true)"> 媒体库 </a-button>
      </template>
    </PageFooter>
    <ArticleDrawer @register="register1" />
  </div>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { MarkDown, MarkDownActionType } from '/@/components/Markdown';
  import { PageFooter } from '/@/components/Page';
  import { useDrawer } from '/@/components/Drawer';
  import ArticleDrawer from './ArticleDrawer.vue';

  export default defineComponent({
    components: { MarkDown, PageFooter, ArticleDrawer },
    setup() {
      const markDownRef = ref<Nullable<MarkDownActionType>>(null);
      const valueRef = ref('');
      const titleValue = '';
      const mdHeight = document.documentElement.clientHeight - 195;
      const [register1, { openDrawer: openDrawer1 }] = useDrawer();

      async function submitAll() {
        try {
          if (tableRef.value) {
            console.log('table data:', tableRef.value.getDataSource());
          }

          const [values, taskValues] = await Promise.all([validate(), validateTaskForm()]);
          console.log('form data:', values, taskValues);
        } catch (error) {}
      }

      return {
        value: valueRef,
        markDownRef,
        titleValue,
        submitAll,
        mdHeight,
        register1,
        openDrawer1,
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
