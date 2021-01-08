<template>
  <div class="p-4">
    <BasicForm @register="registerForm"> </BasicForm>
    <PageFooter>
      <template #right>
        <a-button class="mr-2" type="dashed" @click="getFormValues"> 保存草稿 </a-button>
        <a-button class="mr-2" @click="openDrawer1(true)"> 预览 </a-button>
        <a-button class="mr-2" type="primary" @click="openDrawer1(true)"> 发布 </a-button>
        <a-button class="mr-2" @click="openDrawer1(true)"> 媒体库 </a-button>
      </template>
    </PageFooter>
    <ArticleDrawer @register="register1" />
  </div>
</template>
<script lang="ts">
  import { defineComponent, h } from 'vue';
  import { BasicForm, FormSchema, useForm } from '/@/components/Form/index';
  import { MarkDown } from '/@/components/Markdown';
  import { PageFooter } from '/@/components/Page';
  import { useDrawer } from '/@/components/Drawer';
  import ArticleDrawer from './ArticleDrawer.vue';

  const schemas: FormSchema[] = [
    {
      field: 'title',
      component: 'Input',
      label: '',
      defaultValue: '标题',
      rules: [{ required: true }],
    },
    {
      field: 'markdown',
      component: 'Input',
      label: '',
      defaultValue: 'defaultValue',
      rules: [{ required: true, trigger: 'blur' }],
      render: ({ model, field }) => {
        return h(MarkDown, {
          value: model[field],
          onChange: (value: string) => {
            model[field] = value;
          },
          height: document.documentElement.clientHeight - 220,
        });
      },
    },
  ];

  export default defineComponent({
    components: { MarkDown, PageFooter, ArticleDrawer, BasicForm },
    setup() {
      const [register1, { openDrawer: openDrawer1 }] = useDrawer();

      const [
        registerForm,
        {
          // setFieldsValue,
          getFieldsValue,
        },
      ] = useForm({
        schemas,
        showActionButtonGroup: false,
        actionColOptions: {
          span: 24,
        },
      });

      function getFormValues() {
        const values = getFieldsValue();
        console.log('values:' + JSON.stringify(values));
      }

      return {
        getFormValues,
        registerForm,
        register1,
        openDrawer1,
      };
    },
  });
</script>

<style lang="less" scoped></style>
