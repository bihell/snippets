#  .env.development

```vue
# Whether to open mock
VITE_USE_MOCK = true

# public path
VITE_PUBLIC_PATH = /

# Cross-domain proxy, you can configure multiple
VITE_PROXY=[["/api","http://localhost:3000"]]

# Delete console
VITE_DROP_CONSOLE = false

# Basic interface address SPA
VITE_GLOB_API_URL=http://127.0.0.1:81

# Interface prefix
VITE_GLOB_API_URL_PREFIX=/v1/api/admin
```



# /src/store/modules/user.ts

```
@Action
async login(params: LoginParams, goHome = true): Promise<GetUserInfoByUserIdModel | null> {
  try {
    const data = await loginApi(params);

    const { token } = data;

    // save token
    this.commitTokenState(token);

    // get user info
    const userInfo = await this.getUserInfoAction();

    // const name = FULL_PAGE_NOT_FOUND_ROUTE.name;
    // name && router.removeRoute(name);
    goHome &&
      (await router.push(PageEnum.BASE_HOME).then(() => {
        setTimeout(() => {
          appStore.commitPageLoadingState(false);
        }, 30);
      }));
    return userInfo;
  } catch (error) {
    return null;
  }
}
```

```vue
  @Action
  async getUserInfoAction() {
    // const userInfo = await getUserInfoById({ userId });
    const userInfo = {
      desc: 'manager',
      password: '123456',
      realName: 'dice',
      role: { roleName: 'Super Admin', value: 'super' },
      userId: '1',
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
import { defHttp } from '/@/utils/http/axios';
import {
  LoginParams,
  LoginResultModel,
  GetUserInfoByUserIdParams,
  GetUserInfoByUserIdModel,
} from './model/userModel';

enum Api {
  Login = '/auth/login',
  GetUserInfoById = '/getUserInfoById',
  GetPermCodeByUserId = '/getPermCodeByUserId',
}

/**
 * @description: user login api
 */
export function loginApi(params: LoginParams) {
  return defHttp.request<LoginResultModel>(
    {
      url: Api.Login,
      method: 'POST',
      params,
    },
    {
      errorMessageMode: 'modal',
    }
  );
}

/**
 * @description: getUserInfoById
 */
export function getUserInfoById(params: GetUserInfoByUserIdParams) {
  return defHttp.request<GetUserInfoByUserIdModel>({
    url: Api.GetUserInfoById,
    method: 'GET',
    params,
  });
}

export function getPermCodeByUserId(params: GetUserInfoByUserIdParams) {
  return defHttp.request<string[]>({
    url: Api.GetPermCodeByUserId,
    method: 'GET',
    params,
  });
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

