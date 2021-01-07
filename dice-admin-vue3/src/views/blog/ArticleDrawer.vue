<template>
  <BasicDrawer v-bind="$attrs" title="文章设置" width="30%" show-footer>
    <BasicForm :model="model" layout="vertical" @register="registerForm" />
    <template #footer>
      <a-button> customerFooter</a-button>
    </template>
  </BasicDrawer>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { BasicDrawer } from '/@/components/Drawer';
  import { BasicForm, FormSchema, useForm } from '/@/components/Form/index';
  const schemas: FormSchema[] = [
    {
      field: 'tag',
      component: 'Input',
      label: '标签',
      componentProps: {
        placeholder: '请选择文章标签',
      },
    },
    {
      field: 'category',
      component: 'Input',
      label: '分类',
      componentProps: {
        placeholder: '请选择文章分类',
      },
    },
    {
      field: 'top',
      component: 'Switch',
      label: '是否置顶',
      componentProps: {
        placeholder: '请选择文章分类',
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
        labelWidth: 120,
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
