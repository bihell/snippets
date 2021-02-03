import { BasicColumn, FormProps } from '/@/components/Table';
import { formatToDateTime } from '/@/utils/dateUtil';

export function getUserColumns(): BasicColumn[] {
  return [
    {
      title: 'ID',
      width: 60,
      dataIndex: 'id',
    },
    {
      title: '用户名',
      width: 200,
      dataIndex: 'username',
      align: 'left',
    },
    {
      title: '别名',
      dataIndex: 'screenName',
      width: 200,
      align: 'left',
    },
    {
      title: '邮箱',
      dataIndex: 'email',
      // width: 80,
      align: 'left',
    },
    {
      title: '创建时间',
      sorter: true,
      dataIndex: 'created',
      width: 150,
      customRender: ({ record }) => formatToDateTime(record.created),
    },
    {
      title: '最后登陆时间',
      sorter: true,
      dataIndex: 'logged',
      width: 150,
      customRender: ({ record }) => formatToDateTime(record.logged),
    },
  ];
}

export function getUserFormConfig(): Partial<FormProps> {
  return {
    labelWidth: 10,
    schemas: [
      {
        field: `criteria`,
        label: ` `,
        component: 'Input',
        componentProps: {
          placeholder: '请输入用户名',
        },
        colProps: {
          xl: 14,
          xxl: 7,
        },
      },
    ],
  };
}
