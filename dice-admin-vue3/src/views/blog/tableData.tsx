import { FormProps } from '/@/components/Table';
import { BasicColumn } from '/@/components/Table/src/types/table';
import { formatToDateTime } from '/@/utils/dateUtil';

export function getBasicColumns(): BasicColumn[] {
  return [
    {
      title: 'ID',
      width: 60,
      dataIndex: 'id',
    },
    {
      title: '标题',
      dataIndex: 'title',
      align: 'left',
    },
    {
      title: '分类',
      dataIndex: 'category',
      width: 80,
      align: 'center',
      slots: { customRender: 'category' },
    },
    {
      title: '状态',
      dataIndex: 'status',
      width: 70,
      align: 'center',
      slots: { customRender: 'status' },
    },
    {
      title: '评论',
      dataIndex: 'commentCount',
      width: 60,
      align: 'center',
      slots: { customRender: 'cc' },
    },
    {
      title: '发布日期',
      sorter: true,
      dataIndex: 'createTime',
      width: 150,
      customRender: ({ record }) => formatToDateTime(record.createTime),
    },
    {
      title: '修改日期',
      sorter: true,
      dataIndex: 'updateTime',
      width: 150,
      customRender: ({ record }) => formatToDateTime(record.updateTime),
    },
  ];
}

export function getCommentColumns(): BasicColumn[] {
  return [
    {
      title: 'ID',
      width: 60,
      dataIndex: 'id',
    },
    {
      title: '称呼',
      width: 120,
      dataIndex: 'name',
      align: 'left',
    },
    {
      title: '内容',
      dataIndex: 'content',
      align: 'left',
    },
    {
      title: '邮箱',
      dataIndex: 'email',
      width: 200,
      align: 'left',
    },
    {
      title: '评论日期',
      dataIndex: 'created',
      width: 150,
      align: 'center',
      customRender: ({ record }) => formatToDateTime(record.created),
    },
  ];
}

export function getFormConfig(): Partial<FormProps> {
  return {
    labelWidth: 10,
    schemas: [
      {
        field: `status`,
        label: ` `,
        component: 'Select',
        defaultValue: '',
        // slot: 'advanceBefore',
        componentProps: {
          placeholder: '状态',
          options: [
            {
              label: '不限制',
              value: null,
            },
            {
              label: '已发布',
              value: 'PUBLISHED',
            },
            {
              label: '草稿',
              value: 'DRAFT',
            },
            {
              label: '回收站',
              value: 'RECYCLE',
            },
            {
              label: '私密',
              value: 'INTIMATE',
            },
          ],
        },
        colProps: {
          xl: 4,
          xxl: 2,
        },
      },
      {
        field: `priority`,
        label: ` `,
        component: 'Select',
        defaultValue: '',
        componentProps: {
          placeholder: '是否置顶',
          options: [
            {
              label: '不限制',
              value: null,
            },
            {
              label: '普通',
              value: 0,
            },
            {
              label: '置顶',
              value: 1,
            },
          ],
        },
        colProps: {
          xl: 4,
          xxl: 2,
        },
      },
      {
        field: `title`,
        label: ` `,
        component: 'Input',
        componentProps: {
          placeholder: '搜索标题',
        },
        colProps: {
          xl: 14,
          xxl: 7,
        },
      },
      {
        field: `content`,
        label: ` `,
        component: 'Input',
        componentProps: {
          placeholder: '搜索内容',
        },
        colProps: {
          xl: 14,
          xxl: 7,
        },
      },
    ],
  };
}