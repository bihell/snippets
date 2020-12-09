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
  async login(params: LoginParams, goHome = true): Promise<GetUserInfoByUserIdModel | null> {
    try {
      const data = await loginApi(params);
      const { token, userId } = data;

      // save token
      this.commitTokenState(token);

      // get user info
      const userInfo = await this.getUserInfoAction({ userId });

      // const name = FULL_PAGE_NOT_FOUND_ROUTE.name;
      // name && router.removeRoute(name);
      goHome && router.push(PageEnum.BASE_HOME);
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

