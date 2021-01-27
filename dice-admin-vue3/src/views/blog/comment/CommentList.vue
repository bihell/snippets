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
        :drop-down-actions="[
          {
            label: '编辑',
            onClick: handleEditClick.bind(null, record),
          },
          {
            label: '删除',
            onClick: handleEditClick.bind(null, record),
          },
        ]"
        :divider="false"
      >
        <template #more>
          <a-button shape="circle" class="border-none">
            <FormOutlined />
          </a-button>
        </template>
      </TableAction>
    </template>
    <!--    <template #toolbar>-->
    <!--      <router-link :to="{ name: 'PostEdit' }">-->
    <!--        <a-button type="primary"> <FileAddOutlined />写文章</a-button>-->
    <!--      </router-link>-->
    <!--    </template>-->
  </BasicTable>
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { getCommentColumns } from '../tableData';
  import { Tag, Badge } from 'ant-design-vue';
  import { apiCommentList, postStatus } from '/@/api/blog/blog';
  import { FormOutlined } from '@ant-design/icons-vue';
  import { useRouter } from 'vue-router';

  export default defineComponent({
    components: { BasicTable, Tag, Badge, TableAction, FormOutlined },
    setup() {
      const [registerTable] = useTable({
        title: '文章列表',
        api: apiCommentList,
        columns: getCommentColumns(),
        showTableSetting: true,
        showIndexColumn: false,
        bordered: true,
        actionColumn: {
          width: 50,
          title: '操作',
          align: 'center',
          dataIndex: 'action',
          slots: { customRender: 'action' },
        },
      });

      const status = postStatus();
      const router = useRouter();

      function pushWithQuery(query: any) {
        router.push({
          name: 'PostEdit',
          query: query,
        });
      }

      function handleEditClick(record: any) {
        pushWithQuery({ id: record.id });
      }

      return {
        registerTable,
        status,
        handleEditClick,
      };
    },
  });
</script>

<style lang="less" scoped>
  .border-none {
    border: 0 !important;
  }
</style>
