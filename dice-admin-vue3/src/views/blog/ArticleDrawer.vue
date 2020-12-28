<template>
  <BasicDrawer v-bind="$attrs" @register="register" title="Drawer Title" width="40%">
    <div>
      <BasicForm @register="registerForm" />
    </div>
    <div class="bottom-control">
      <a-button class="mb-2" type="dashed" @click="advancedVisible = true"> 高级 </a-button>
      <a-space>
        <a-button type="dashed" @click="advancedVisible = true"> 高级 </a-button>
      </a-space>
    </div>
  </BasicDrawer>
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';
  import { BasicForm, FormSchema, useForm } from '/@/components/Form/index';

  const schemas: FormSchema[] = [
    {
      field: 'field1',
      component: 'Input',
      label: '字段1',
      colProps: {
        span: 12,
      },
      defaultValue: '111222',
    },
    {
      field: 'field2',
      component: 'Input',
      label: '字段2',
      colProps: {
        span: 12,
      },
    },
  ];
  export default defineComponent({
    components: { BasicDrawer, BasicForm },
    setup() {
      const [registerForm, { setFieldsValue }] = useForm({
        labelWidth: 120,
        schemas,
        showActionButtonGroup: false,
        actionColOptions: {
          span: 24,
        },
      });
      const [register] = useDrawerInner((data) => {
        // 方式1
        setFieldsValue({
          field2: data.data,
          field1: data.info,
        });
      });
      return { register, schemas, registerForm };
    },
  });
</script>

<style lang="less" scoped>
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

  .mb-2 {
    margin-bottom: 1em !important;
  }
</style>
