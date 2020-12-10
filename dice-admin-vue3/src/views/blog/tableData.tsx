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

// export const getAdvanceSchema = (itemNumber = 6): FormSchema[] => {
//   const arr: any = [];
//   // for (let index = 0; index < itemNumber; index++) {
//   //   arr.push({
//   //     field: `field${index}`,
//   //     label: `字段${index}`,
//   //     component: 'Input',
//   //     colProps: {
//   //       xl: 12,
//   //       xxl: 8,
//   //     },
//   //   });
//   // }
//   return arr;
// };

export function getFormConfig(): Partial<FormProps> {
  return {
    labelWidth: 100,
    schemas: [
      // ...getAdvanceSchema(5),
      {
        field: `field11`,
        label: `字段33`,
        component: 'Select',
        defaultValue: '1',
        componentProps: {
          options: [
            {
              label: '选项1',
              value: '1',
            },
            {
              label: '选项2',
              value: '2',
            },
          ],
        },
        colProps: {
          xl: 12,
          xxl: 8,
        },
      },
    ],
  };
}
