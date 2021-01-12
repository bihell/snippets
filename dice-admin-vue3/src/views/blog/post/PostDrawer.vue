<template>
  <BasicDrawer v-bind="$attrs" title="文章设置" width="20%" show-footer @register="register">
    <BasicForm :model="model" layout="vertical" @register="registerForm" />
    <template #footer>
      <a-button class="mr-2" type="dashed"> 保存草稿 </a-button>
      <a-button class="mr-2" type="primary" @click="getFormValues"> 发布 </a-button>
      <a-button>发布并查看</a-button>
    </template>
  </BasicDrawer>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';
  import { BasicForm, FormSchema, useForm } from '/@/components/Form/index';
  import { metaListApi } from '/@/api/blog/blog';
  import 'moment/dist/locale/zh-cn';
  const schemas: FormSchema[] = [
    {
      field: 'tags',
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
      componentProps: {},
      colProps: {
        span: 10,
      },
    },
    {
      field: 'allowComment',
      component: 'Switch',
      label: '开启评论',
      componentProps: {},
      colProps: {
        span: 10,
      },
    },
    {
      field: 'createTime',
      component: 'DatePicker',
      label: '创建日期',
      componentProps: {
        showTime: true,
        onOk: (e: any) => {
          console.log(e);
        },
      },
    },
    {
      field: 'updateTime',
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
      const [registerForm, { setFieldsValue, getFieldsValue }] = useForm({
        schemas,
        showActionButtonGroup: false,
        actionColOptions: {
          span: 24,
        },
      });

      const [register] = useDrawerInner((data) => {
        if (typeof data.tags === 'string') {
          data.tags = data.tags.split(',');
          // data.createTime = moment(data.createTime);
        }
        setFieldsValue(data);
      });

      function getFormValues() {
        const values = getFieldsValue();
        console.log('values:' + JSON.stringify(values));
      }

      return {
        register,
        getFormValues,
        schemas,
        registerForm,
        model: modelRef,
      };
    },
  });
</script>
