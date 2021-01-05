<template>
  <BasicTable @register="registerTable">
    <template #category="{ record }">
      <Tag color="blue">
        {{ record.category }}
      </Tag>
    </template>
    <template #status="{ record }">
      <Tag color="blue">
        {{ status[record.status].text }}
      </Tag>
    </template>
    <template #cc="{ record }">
      <Badge :count="record.commentCount" show-zero />
    </template>
    <template #action="{ record }">
      <TableAction
        :actions="[
          {
            label: '编辑',
            icon: 'ic:outline-delete-outline',
            onClick: handleDelete.bind(null, record),
          },
        ]"
        :drop-down-actions="[
          {
            label: '编辑',
            onClick: handleDelete.bind(null, record),
          },
          {
            label: '删除',
            onClick: handleOpen.bind(null, record),
          },
        ]"
      />
    </template>
  </BasicTable>
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { getBasicColumns, getFormConfig } from './tableData';
  import { Tag, Badge } from 'ant-design-vue';
  import { articleListApi, postStatus } from '/@/api/blog/blog';

  export default defineComponent({
    components: { BasicTable, Tag, Badge, TableAction },
    setup() {
      const [registerTable] = useTable({
        title: '文章列表',
        api: articleListApi,
        columns: getBasicColumns(),
        useSearchForm: true,
        formConfig: getFormConfig(),
        showTableSetting: true,
        showIndexColumn: false,
        bordered: true,
        actionColumn: {
          width: 100,
          title: '操作',
          align: 'center',
          dataIndex: 'action',
          slots: { customRender: 'action' },
        },
      });

      const status = postStatus();

      function handleDelete(record: any) {
        console.log('点击了删除', record);
      }
      function handleOpen(record: any) {
        console.log('点击了启用', record);
      }

      return {
        registerTable,
        status,
        handleDelete,
        handleOpen,
      };
    },
  });
</script>
