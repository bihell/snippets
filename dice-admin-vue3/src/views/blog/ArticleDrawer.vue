<template>
  <BasicDrawer v-bind="$attrs" title="文章设置" width="20%" show-footer>
    <BasicForm :model="model" layout="vertical" @register="registerForm" />
    <template #footer>
      <a-button class="mr-2" type="dashed"> 保存草稿 </a-button>
      <a-button class="mr-2" type="primary"> 发布 </a-button>
      <a-button>发布并查看</a-button>
    </template>
  </BasicDrawer>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { BasicDrawer } from '/@/components/Drawer';
  import { BasicForm, FormSchema, useForm } from '/@/components/Form/index';
  import { metaListApi } from '/@/api/blog/blog';
  const schemas: FormSchema[] = [
    {
      field: 'tag',
      component: 'ApiSelect',
      label: '标签',
      componentProps: {
        mode: 'multiple',
        placeholder: '请选择文章标签',
        api: metaListApi,
        params: { type: 'tag' },
      },
      colProps: {
        span: 24,
      },
    },
    {
      field: 'category',
      component: 'ApiSelect',
      label: '分类',
      componentProps: {
        placeholder: '请选择文章分类',
        api: metaListApi,
        params: { type: 'category' },
      },
      colProps: {
        span: 24,
      },
    },
    {
      field: 'top',
      component: 'Switch',
      label: '是否置顶',
      layout: 'horizontal',
      componentProps: {},
      colProps: {
        span: 10,
      },
    },
    {
      field: 'allowComment',
      component: 'Switch',
      label: '开启评论',
      layout: 'horizontal',
      componentProps: {},
      colProps: {
        span: 10,
      },
    },
    {
      field: 'createDate',
      component: 'DatePicker',
      label: '创建日期',
      componentProps: {
        showTime: true,
      },
    },
    {
      field: 'modifyDate',
      component: 'DatePicker',
      label: '修改日期',
      componentProps: {
        showTime: true,
      },
    },
  ];

  export default defineComponent({
    components: { BasicDrawer, BasicForm },
    setup() {
      const modelRef = ref({});
      const [
        registerForm,
        {
          // setFieldsValue,
          // setProps
        },
      ] = useForm({
        schemas,
        showActionButtonGroup: false,
        actionColOptions: {
          span: 24,
        },
      });

      // const [register] = useModalInner((data) => {
      //   // 方式1
      //   // setFieldsValue({
      //   //   field2: data.data,
      //   //   field1: data.info,
      //   // });
      //
      //   // 方式2
      //   modelRef.value = { field2: data.data, field1: data.info };
      //
      //   // setProps({
      //   //   model:{ field2: data.data, field1: data.info }
      //   // })
      // });
      return {
        // register,
        schemas,
        registerForm,
        model: modelRef,
      };
    },
  });
</script>
