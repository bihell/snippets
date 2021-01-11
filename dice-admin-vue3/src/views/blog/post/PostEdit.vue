<template>
  <div class="p-4">
    <BasicForm @register="registerForm" />
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
  import { defineComponent, h, onMounted, ref } from 'vue';
  import { BasicForm, FormSchema, useForm } from '/@/components/Form/index';
  import { MarkDown } from '/@/components/Markdown';
  import { PageFooter } from '/@/components/Page';
  import { useDrawer } from '/@/components/Drawer';
  import ArticleDrawer from './PostDrawer.vue';
  import { useRoute } from 'vue-router';
  import { apiGetPost } from '/@/api/blog/blog';

  const schemas: FormSchema[] = [
    {
      field: 'title',
      component: 'Input',
      label: '',
      componentProps: {
        placeholder: '文章标题',
      },
      rules: [{ required: true }],
    },
    {
      field: 'markdown',
      component: 'Input',
      label: '',
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
    components: { PageFooter, ArticleDrawer, BasicForm },
    setup() {
      const loading = ref(false);
      const [register1, { openDrawer: openDrawer1 }] = useDrawer();
      const route = useRoute();
      let postInfo: { title: String; content: String };
      const loadDataList = async () => {
        try {
          loading.value = true;
          postInfo = await apiGetPost(Number(route.query.id));
          console.log(postInfo.title);
          setFromValues();
        } catch (error) {
        } finally {
          setTimeout(() => {
            loading.value = false;
          }, 500);
        }
      };

      const [registerForm, { setFieldsValue, getFieldsValue }] = useForm({
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

      function setFromValues() {
        setFieldsValue({ title: postInfo.title, markdown: postInfo.content });
      }

      onMounted(() => {
        loadDataList();
      });

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
