import { FormProps } from '/@/components/Table';
import { BasicColumn } from '/@/components/Table/src/types/table';

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
    },
    {
      title: '分类',
      dataIndex: 'category',
      width: 150,
    },
    {
      title: '状态',
      dataIndex: 'status',
    },
    {
      title: '评论',
      dataIndex: 'commentCount',
    },
    {
      title: '发布日期',
      sorter: true,
      dataIndex: 'publish',
      width: 150,
    },
    {
      title: '修改日期',
      sorter: true,
      dataIndex: 'updateTime',
      width: 150,
    },
  ];
}

export function getFormConfig(): Partial<FormProps> {
  return {
    labelWidth: 70,
    schemas: [
      {
        field: `status`,
        label: `文章状态`,
        component: 'Select',
        defaultValue: '',
        componentProps: {
          options: [
            {
              label: '不限制',
              value: '',
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
          xl: 5,
          xxl: 3,
        },
      },
    ],
  };
}
