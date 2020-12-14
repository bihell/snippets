#  	.env.development

```vue
# Whether to open mock
VITE_USE_MOCK = true

# Basic interface address SPA
VITE_GLOB_API_URL=http://127.0.0.1:81

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
      goHome && router.replace(PageEnum.BASE_HOME);
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

# /src/api/sys/user.ts

```
enum Api {
  Login = '/auth/login',
  GetUserInfoById = '/getUserInfoById',
  GetPermCodeByUserId = '/getPermCodeByUserId',
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
  history: createWebHistory('admin'),
  routes: basicRoutes as RouteRecordRaw[],
  strict: true,
  scrollBehavior: scrollBehavior,
});
```

# src/router/menus/modules/blog.ts

```
import type { MenuModule } from '/@/router/types.d';
import { t } from '/@/hooks/web/useI18n';

const menu: MenuModule = {
  orderNo: 0,
  menu: {
    path: '/blog',
    name: t('routes.blog.blog'),
    children: [
      {
        path: 'article',
        name: t('routes.blog.article'),
      },
    ],
  },
};
export default menu;

```

# src/locales/lang/en/routes/blog.ts

```
export default {
  blog: 'Blog',

  article: 'Article',
};
```

# src/locales/lang/zh_CN/routes/blog.ts

```
export default {
  blog: '博客',

  article: '文章列表',
};
```

# src/router/routes/modules/blog.ts

```
import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';
import { t } from '/@/hooks/web/useI18n';

const blog: AppRouteModule = {
  path: '/blog',
  name: 'Blog',
  component: LAYOUT,
  redirect: '/blog/article',
  meta: {
    icon: 'carbon:blog',
    title: t('routes.blog.blog'),
  },
  children: [
    {
      path: 'article',
      name: 'article',
      component: () => import('/@/views/blog/Articles.vue'),
      meta: {
        title: t('routes.blog.article'),
      },
    },
  ],
};

export default blog;
```

# src/layouts/default/footer/index.tsx

注释掉页脚

```
import './index.less';

import { defineComponent } from 'vue';
import { Layout } from 'ant-design-vue';
//
// import { GithubFilled } from '@ant-design/icons-vue';
//
// import { DOC_URL, GITHUB_URL, SITE_URL } from '/@/settings/siteSetting';
// import { openWindow } from '/@/utils';
//
// import { useI18n } from '/@/hooks/web/useI18n';

export default defineComponent({
  name: 'LayoutContent',
  setup() {
    // const { t } = useI18n();
    return () => {
      return (
        <Layout.Footer class="layout-footer">
          {() => (
            <>
              {/*<div class="layout-footer__links">*/}
              {/*  <a onClick={() => openWindow(SITE_URL)}>{t('layout.footer.onlinePreview')}</a>*/}
              {/*  <GithubFilled onClick={() => openWindow(GITHUB_URL)} class="github" />*/}
              {/*  <a onClick={() => openWindow(DOC_URL)}>{t('layout.footer.onlineDocument')}</a>*/}
              {/*</div>*/}
              {/*<div>Copyright &copy;2020 Vben Admin</div>*/}
            </>
          )}
        </Layout.Footer>
      );
    };
  },
});

```

# src/views/blog/Articles.vue

```
<template>
  <BasicTable @register="registerTable" />
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { BasicTable, useTable } from '/@/components/Table';
  import { getBasicColumns, getFormConfig } from './tableData';

  import { articleListApi } from '/@/api/blog/blog';

  export default defineComponent({
    components: { BasicTable },
    setup() {
      const [registerTable] = useTable({
        title: '文章列表',
        api: articleListApi,
        columns: getBasicColumns(),
        useSearchForm: true,
        formConfig: getFormConfig(),
        showTableSetting: true,
      });

      return {
        registerTable,
      };
    },
  });
</script>

```

# src/views/blog/tableData.tsx

```
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
```

