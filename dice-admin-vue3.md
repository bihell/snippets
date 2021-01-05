# .eslintrc.js

```
  rules: {
    'prettier/prettier':'off',
```

#  	.env.development

```vue
# Whether to open mock
VITE_USE_MOCK = false

# public path
VITE_PUBLIC_PATH = /

# Cross-domain proxy, you can configure multiple
VITE_PROXY=[["/api","http://localhost:3000"],["/upload","http://localhost:3001/upload"]]
# VITE_PROXY=[["/api","https://vvbin.cn/test"]]

# Delete console
VITE_DROP_CONSOLE = false

# Basic interface address SPA
VITE_GLOB_API_URL=http://127.0.0.1:9091

# File upload address， optional
VITE_GLOB_UPLOAD_URL=/upload

# Interface prefix
VITE_GLOB_API_URL_PREFIX=/v1/api/admin
```



# /src/store/modules/user.ts

```
import { loginApi } from '/@/api/sys/user';
```

```
  /**
   * @description: login
   */
  @Action
  async login(
    params: LoginParams & {
      goHome?: boolean;
      mode?: ErrorMessageMode;
    }
  ): Promise<GetUserInfoByUserIdModel | null> {
    try {
      const { goHome = true, mode, ...loginParams } = params;
      const data = await loginApi(loginParams, mode);

      const { token, userId } = data;

      // save token
      this.commitTokenState(token);

      // get user info
      const userInfo = await this.getUserInfoAction({ userId });

      // const name = FULL_PAGE_NOT_FOUND_ROUTE.name;
      // name && router.removeRoute(name);
      goHome && (await router.replace(PageEnum.BASE_HOME));
      return userInfo;
    } catch (error) {
      return null;
    }
  }
```

```vue
  @Action
  async getUserInfoAction({ userId }: GetUserInfoByUserIdParams) {
    // const userInfo = await getUserInfoById({ userId });
    const userInfo = {
      desc: 'manager',
      password: '123456',
      realName: 'dice',
      role: { roleName: 'Super Admin', value: 'super' },
      userId: userId,
      username: 'dice',
    };
    const { role } = userInfo;
    const roleList = [role.value] as RoleEnum[];
    this.commitUserInfoState(userInfo);
    this.commitRoleListState(roleList);
    return userInfo;
  }
```

# src/enums/httpEnum.ts

```
/**
 * @description: Request result set
 */
export enum ResultEnum {
  SUCCESS = 200,
  ERROR = 1,
  TIMEOUT = 401,
  TYPE = 'success',
}
```

# src/router/index.ts

```
import { createRouter, createWebHistory } from 'vue-router';
```

```
// app router
const router = createRouter({
  history: createWebHistory(),
  routes: basicRoutes as RouteRecordRaw[],
  strict: true,
  scrollBehavior: scrollBehavior,
});
```

# src/router/menus/modules/blog.ts

```
import type { MenuModule } from '/@/router/types.d';

const menu: MenuModule = {
  orderNo: 0,
  menu: {
    path: '/blog',
    name: '博客',
    children: [
      {
        path: 'articles',
        name: '文章列表',
      },
      {
        path: 'article',
        name: '编辑文章',
      },
    ],
  },
};
export default menu;
```

# src/router/routes/modules/blog.ts

```
import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';

const blog: AppRouteModule = {
  path: '/blog',
  name: 'Blog',
  component: LAYOUT,
  redirect: '/blog/article',
  meta: {
    icon: 'carbon:blog',
    title: '博客',
  },
  children: [
    {
      path: 'articles',
      name: 'articles',
      component: () => import('/@/views/blog/Articles.vue'),
      meta: {
        title: '文章列表',
      },
    },
    {
      path: 'article',
      name: 'article',
      component: () => import('/@/views/blog/Article.vue'),
      meta: {
        title: '编辑文章',
      },
    },
  ],
};

export default blog;

```

# src/views/blog/Articles.vue

```vue
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
      <Badge
        :count="record.commentCount"
        show-zero
      />
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


```

# src/views/blog/tableData.tsx

```
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

export function getFormConfig(): Partial<FormProps> {
  return {
    labelWidth: 10,
    schemas: [
      {
        field: `status`,
        label: ` `,
        component: 'Select',
        defaultValue: '',
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

```

# /src/views/blog/ArticleDrawer.vue

```vue
<template>
  <BasicDrawer v-bind="$attrs" title="Modal Title" width="50%" showFooter @ok="handleOk">
    <p class="h-20" v-for="index in 40" :key="index">根据屏幕高度自适应</p>
    <template #insertFooter>
      <a-button> btn</a-button>
    </template>
    <template #centerFooter>
      <a-button> btn2</a-button>
    </template>

    <template #appendFooter>
      <a-button> btn3</a-button>
    </template>

  <template #footer>
      <a-button> customerFooter</a-button>
    </template>
  </BasicDrawer>
</template>
<script lang="ts">
import { defineComponent } from 'vue';
import { BasicDrawer } from '/@/components/Drawer';
export default defineComponent({
  components: { BasicDrawer },
  setup() {
    return {
      handleOk: () => {
        console.log('=====================');
        console.log('ok');
        console.log('======================');
      },
    };
  },
});
</script>


```

# src/views/blog/Article.vue

```vue
<template>
  <div class="p-4">
    <a-input
      v-model:value="titleValue"
      class="mb-2"
      placeholder="请输入标题"
    />
    <MarkDown
      ref="markDownRef"
      v-model:value="value"
      v-model:height="mdHeight"
    />
    <PageFooter>
      <template #right>
        <a-button
          type="primary"
          @click="submitAll"
        >
          提交
        </a-button>
        <a-button type="primary" class="my-4" @click="openDrawer1(true)">打开Drawer</a-button>
      </template>
    </PageFooter>
    <ArticleDrawer @register="register1" />
  </div>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { MarkDown, MarkDownActionType } from '/@/components/Markdown';
  import { PageFooter } from '/@/components/Page';
  import { useDrawer } from '/@/components/Drawer';
  import ArticleDrawer from './ArticleDrawer.vue';

  export default defineComponent({
    components: { MarkDown,PageFooter,ArticleDrawer },
    setup() {
      const markDownRef = ref<Nullable<MarkDownActionType>>(null);
      const valueRef = ref('');
      const titleValue = '';
      const mdHeight = document.documentElement.clientHeight - 195;
      const [register1, { openDrawer: openDrawer1 }] = useDrawer();


      async function submitAll() {
        try {
          if (tableRef.value) {
            console.log('table data:', tableRef.value.getDataSource());
          }

          const [values, taskValues] = await Promise.all([validate(), validateTaskForm()]);
          console.log('form data:', values, taskValues);
        } catch (error) {}
      }

      return {
        value: valueRef,
        markDownRef,
        titleValue,
        submitAll,
        mdHeight,
        register1,
        openDrawer1,
      };
    },
  });
</script>

<style lang="less" scoped>
  .app-container {
    padding: 15px;
  }

  .mb-3 {
    margin-bottom: 1em !important;
  }

  .mb-2 {
    margin-bottom: 0.5em !important;
  }

</style>

```



# /src/api/sys/user.ts

```
enum Api {
  Login = '/auth/login',
  GetUserInfoById = '/getUserInfoById',
  GetPermCodeByUserId = '/getPermCodeByUserId',
}
```

# src/api/model/baseModel.ts

```
export interface BasicFetchResult<T extends any> {
  list: T;
  total: number;
}
```

# src/api/blog/model/blogModel.ts

```
import { BasicPageParams, BasicFetchResult } from '/@/api/model/baseModel';
/**
 * @description: Request list interface parameters
 */
export type ArticleListParams = BasicPageParams;

export interface ArticleListItem {
  createTime: string;
  updateTime: string;
  creator: number;
  modifier: number;
  deleted: number;
  id: number;
  title: string;
  content: string;
  hits: number;
  tags: string;
  category: string;
  priority: number;
  status: string;
  type: string;
  allowComment: boolean;
  commentCount: number;
}

/**
 * @description: Request list return value
 */
export type ArticleListGetResultModel = BasicFetchResult<ArticleListItem>;

```

# /src/api/blog/blog.ts

```
import { defHttp } from '/@/utils/http/axios';
import { ArticleListParams, ArticleListGetResultModel } from './model/blogModel';

enum Api {
  Article_LIST = '/article/getPageList',
}

/**
 * @description: Get sample list value
 */
export function articleListApi(params: ArticleListParams) {
  return defHttp.request<ArticleListGetResultModel>({
    url: Api.Article_LIST,
    method: 'POST',
    params,
    headers: {
      ignoreCancelToken: true,
    },
  });
}

export function postStatus() {
  return ({
    PUBLISHED: {
      value: 'PUBLISHED',
      color: 'green',
      status: 'success',
      text: '已发布'
    },
    DRAFT: {
      value: 'DRAFT',
      color: 'warning',
      status: 'warning',
      text: '草稿'
    },
    RECYCLE: {
      value: 'RECYCLE',
      color: 'danger',
      status: 'error',
      text: '回收站'
    },
    INTIMATE: {
      value: 'INTIMATE',
      color: 'blue',
      status: 'success',
      text: '私密'
    }
  })
}


```

# src/components/Table/src/**const.ts**

```
// 通用接口字段设置
// 支持 xxx.xxx.xxx格式
export const FETCH_SETTING = {
  // 传给后台的当前页字段名
  pageField: 'pageIndex',
  // 传给后台的每页显示记录数字段名
  sizeField: 'pageSize',
  // 接口返回的表格数据字段名
  listField: 'records',
  // 接口返回的表格总数字段名
  totalField: 'total',
};

```

