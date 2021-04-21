# .eslintrc.js

```
  rules: {
    'prettier/prettier': 'off',
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
      userId: userId,
      username: 'vben',
      realName: 'Vben Admin',
      desc: 'manager',
      password: '123456',
      token: 'fakeToken1',
      roles: [{ roleName: 'Super Admin', value: 'super' }],
    };
    const { roles } = userInfo;
    const roleList = roles.map((item) => item.value) as RoleEnum[];
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

```javascript
import type { MenuModule } from '/@/router/types.d';

const menu: MenuModule = {
  orderNo: 0,
  menu: {
    path: '/blog',
    name: '博客',
    children: [
      {
        path: 'list',
        name: '文章列表',
      },
    ],
  },
};
export default menu;
```

# src/router/routes/modules/blog.ts

```javascript
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
      path: 'list',
      name: 'PostList',
      component: () => import('/@/views/blog/post/PostList.vue'),
      meta: {
        title: '文章列表',
      },
    },
    {
      path: 'edit',
      name: 'PostEdit',
      component: () => import('/@/views/blog/post/PostEdit.vue'),
      meta: {
        title: '编辑文章',
      },
    },
  ],
};

export default blog;
```

# src/views/blog/post/PostList.vue

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
    <template #toolbar>
      <router-link :to="{ name: 'PostEdit' }">
        <a-button type="primary"> <FileAddOutlined />写文章</a-button>
      </router-link>
    </template>
  </BasicTable>
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { getBasicColumns, getFormConfig } from './tableData';
  import { Tag, Badge } from 'ant-design-vue';
  import { articleListApi, postStatus } from '/@/api/blog/blog';
  import { FormOutlined, FileAddOutlined } from '@ant-design/icons-vue';
  import {useRouter} from 'vue-router'

  export default defineComponent({
    components: { BasicTable, Tag, Badge, TableAction, FormOutlined, FileAddOutlined },
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
          width: 50,
          title: '操作',
          align: 'center',
          dataIndex: 'action',
          slots: { customRender: 'action' },
        },
      });

      const status = postStatus();
      const router = useRouter()

      function  pushWithQuery(query: any)  {
        router.push({
          name: 'PostEdit',
          query: query
        })
      }

      function handleEditClick(record: any) {
        pushWithQuery({id:record.id})
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

# /src/views/blog/PostDrawer.vue

```vue
<template>
  <BasicDrawer v-bind="$attrs" title="文章设置" width="20%" show-footer @register="register">
    <BasicForm :model="model" layout="vertical" @register="registerForm" />
    <template #footer>
      <a-button class="mr-2" type="dashed"> 保存草稿 </a-button>
      <a-button class="mr-2" type="primary" @click="getFormValues"> 发布 </a-button>
      <a-button>发布并查看</a-button>
    </template>
  </BasicDrawer>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';
  import { BasicForm, FormSchema, useForm } from '/@/components/Form/index';
  import { metaListApi } from '/@/api/blog/blog';
  import 'moment/dist/locale/zh-cn';
  const schemas: FormSchema[] = [
    {
      field: 'tags',
      component: 'ApiSelect',
      label: '标签',
      componentProps: {
        mode: 'multiple',
        placeholder: '请选择文章标签',
        api: metaListApi,
        params: { type: 'tag' },
      },
      colProps: {
        span: 24,
      },
    },
    {
      field: 'category',
      component: 'ApiSelect',
      label: '分类',
      componentProps: {
        placeholder: '请选择文章分类',
        api: metaListApi,
        params: { type: 'category' },
      },
      colProps: {
        span: 24,
      },
    },
    {
      field: 'top',
      component: 'Switch',
      label: '是否置顶',
      componentProps: {},
      colProps: {
        span: 10,
      },
    },
    {
      field: 'allowComment',
      component: 'Switch',
      label: '开启评论',
      componentProps: {},
      colProps: {
        span: 10,
      },
    },
    {
      field: 'createTime',
      component: 'DatePicker',
      label: '创建日期',
      componentProps: {
        showTime: true,
        onOk: (e: any) => {
          console.log(e);
        },
      },
    },
    {
      field: 'updateTime',
      component: 'DatePicker',
      label: '修改日期',
      componentProps: {
        showTime: true,
      },
    },
  ];

  export default defineComponent({
    components: { BasicDrawer, BasicForm },
    setup() {
      const modelRef = ref({});
      const [registerForm, { setFieldsValue, getFieldsValue }] = useForm({
        schemas,
        showActionButtonGroup: false,
        actionColOptions: {
          span: 24,
        },
      });

      const [register] = useDrawerInner((data) => {
        if (typeof data.tags === 'string') {
          data.tags = data.tags.split(',');
          // data.createTime = moment(data.createTime);
        }
        setFieldsValue(data);
      });

      function getFormValues() {
        const values = getFieldsValue();
        console.log('values:' + JSON.stringify(values));
      }

      return {
        register,
        getFormValues,
        schemas,
        registerForm,
        model: modelRef,
      };
    },
  });
</script>
```

# src/views/blog/post/PostEdit.vue

```vue
<template>
  <div class="p-4">
    <BasicForm @register="registerForm" />
    <PageFooter>
      <template #right>
        <a-button class="mr-2" type="dashed" @click="saveDraft"> 保存草稿 </a-button>
        <a-button class="mr-2" @click="openDrawer1(true)"> 预览 </a-button>
        <a-button class="mr-2" type="primary" @click="send"> 发布 </a-button>
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
  import { apiGetPost, apiSavePost } from '/@/api/blog/blog';
  import { PostItem } from '/@/api/blog/model/blogModel.ts';

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
      field: 'content',
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
      let postInfo: PostItem;

      const loadDataList = async () => {
        try {
          loading.value = true;
          postInfo = await apiGetPost(Number(route.query.id));
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

      function saveDraft() {
        updatePostInfo();
        apiSavePost(postInfo);
      }

      function getFormValues() {
        const values = getFieldsValue();
        console.log('values:' + JSON.stringify(values));
      }

      function updatePostInfo() {
        const values = getFieldsValue();
        postInfo.tags = postInfo.tags.toString();
        Object.assign(postInfo, values);
      }

      function setFromValues() {
        setFieldsValue(postInfo);
      }

      function send() {
        updatePostInfo();
        openDrawer1(true, postInfo);
      }

      onMounted(() => {
        loadDataList();
      });

      return {
        getFormValues,
        saveDraft,
        send,
        registerForm,
        register1,
        openDrawer1,
      };
    },
  });
</script>

<style lang="less" scoped></style>
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

```javascript
import { BasicPageParams, BasicFetchResult } from '/@/api/model/baseModel';
/**
 * @description: Request list interface parameters
 */
export type ArticleListParams = BasicPageParams;

export interface PostItem {
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

export interface OptionsParams {
  type: string;
}

/**
 * @description: Request list return value
 */
export type ArticleListGetResultModel = BasicFetchResult<PostItem>;

export interface OptionsItem {
  label: string;
  value: string;
}

/**
 * @description: Request list return value
 */
export type OptionsGetResultModel = BasicFetchResult<OptionsItem[]>;
```

# /src/api/blog/blog.ts

```javascript
import { defHttp } from '/@/utils/http/axios';
import {
  ArticleListParams,
  ArticleListGetResultModel,
  OptionsGetResultModel,
  OptionsParams,
  PostItem,
} from './model/blogModel';

enum Api {
  ARTICLE_LIST = '/article/getPageList',
  POST = '/article/',
  META_LIST = '/meta/meta_list',
}

export function apiGetPost(id: number) {
  return defHttp.request<PostItem>({
    url: Api.POST + id,
    method: 'GET',
  });
}

export function apiSavePost(params: PostItem) {
  return defHttp.request({
    url: Api.POST,
    method: 'post',
    params,
  });
}

export function articleListApi(params: ArticleListParams) {
  return defHttp.request<ArticleListGetResultModel>({
    url: Api.ARTICLE_LIST,
    method: 'POST',
    params,
  });
}

export function metaListApi(params: OptionsParams) {
  return defHttp.request<OptionsGetResultModel>({
    url: Api.META_LIST,
    method: 'GET',
    params,
    headers: {
      ignoreCancelToken: true,
    },
  });
}

export function postStatus() {
  return {
    PUBLISHED: {
      value: 'PUBLISHED',
      color: 'green',
      status: 'success',
      text: '已发布',
    },
    DRAFT: {
      value: 'DRAFT',
      color: 'warning',
      status: 'warning',
      text: '草稿',
    },
    RECYCLE: {
      value: 'RECYCLE',
      color: 'danger',
      status: 'error',
      text: '回收站',
    },
    INTIMATE: {
      value: 'INTIMATE',
      color: 'blue',
      status: 'success',
      text: '私密',
    },
  };
}
```

# src/settings/componentSetting.ts

```
export default {
  // basic-table setting
  table: {
    // Form interface request general configuration
    // support xxx.xxx.xxx
    fetchSetting: {
      // The field name of the current page passed to the background
      pageField: 'pageIndex',
      // The number field name of each page displayed in the background
      sizeField: 'pageSize',
      // Field name of the form data returned by the interface
      listField: 'records',
      // Total number of tables returned by the interface field name
      totalField: 'total',
    },
```

# src/store/index.ts

```js
import type { App } from 'vue';
import {
  createStore,
  // createLogger, Plugin
} from 'vuex';
import { config } from 'vuex-module-decorators';
import { isDevMode } from '/@/utils/env';
import { test } from '/@/views/test/vuex/test';
import { albums } from '/@/views/test/photos/albums';
import { photos } from '/@/views/test/photos/photos';

config.rawError = true;
const isDev = isDevMode();
// const plugins: Plugin<any>[] = isDev ? [createLogger()] : [];

const store = createStore({
  modules: { test, albums, photos },
  strict: isDev,
  // plugins,
});

export function setupStore(app: App<Element>) {
  app.use(store);
}

export default store;
```



# 删文件

/Users/haseochen/Documents/SourceCode/GitHub/snippets/dice-admin-vue3/CHANGELOG.en_US.md
/Users/haseochen/Documents/SourceCode/GitHub/snippets/dice-admin-vue3/CHANGELOG.md
/Users/haseochen/Documents/SourceCode/GitHub/snippets/dice-admin-vue3/CHANGELOG.zh_CN.md

