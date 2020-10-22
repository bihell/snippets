import { appStore } from './app';
import type {
  LoginParams,
  GetUserInfoByUserIdModel,
  GetUserInfoByUserIdParams,
} from '/@/api/sys/model/userModel';

import store from '/@/store/index';
import { VuexModule, Module, getModule, Mutation, Action } from 'vuex-module-decorators';
import { hotModuleUnregisterModule } from '/@/utils/helper/vuexHelper';

import { PageEnum } from '/@/enums/pageEnum';
import { RoleEnum } from '/@/enums/roleEnum';
import { ROLES_KEY, TOKEN_KEY, USER_INFO_KEY } from '/@/enums/cacheEnum';

import { useMessage } from '/@/hooks/web/useMessage';

import router, { resetRouter } from '/@/router';
import { permissionStore } from './permission';
import { tabStore } from './tab';

import { loginApi, getUserInfoById } from '/@/api/sys/user';

import { setSession, getSession, clearSession, clearLocal } from '/@/utils/helper/persistent';
// import { FULL_PAGE_NOT_FOUND_ROUTE } from '/@/router/constant';

export type UserInfo = Omit<GetUserInfoByUserIdModel, 'roles'>;

const NAME = 'user';
hotModuleUnregisterModule(NAME);
@Module({ namespaced: true, name: NAME, dynamic: true, store })
class User extends VuexModule {
  // user info
  private userInfoState: UserInfo | null = null;

  // token
  private tokenState = '';

  // roleList
  private roleListState: RoleEnum[] = [];

  get getUserInfoState(): UserInfo {
    return this.userInfoState || (getSession(USER_INFO_KEY) as UserInfo) || {};
  }

  get getTokenState(): string {
    return this.tokenState || (getSession(TOKEN_KEY) as string);
  }

  get getRoleListState(): RoleEnum[] {
    return this.roleListState.length > 0
      ? this.roleListState
      : (getSession(ROLES_KEY) as RoleEnum[]);
  }

  @Mutation
  resetState(): void {
    this.userInfoState = null;
    this.tokenState = '';
    this.roleListState = [];
    // permissionStore.commitReset();
  }

  @Mutation
  commitUserInfoState(info: UserInfo): void {
    this.userInfoState = info;
    if (info) {
      setSession(USER_INFO_KEY, info);
    }
  }

  @Mutation
  commitRoleListState(roleList: RoleEnum[]): void {
    this.roleListState = roleList;
    if (roleList) {
      setSession(ROLES_KEY, roleList);
    }
  }

  @Mutation
  commitTokenState(info: string): void {
    this.tokenState = info;
    if (info) {
      setSession(TOKEN_KEY, info);
    }
  }

  /**
   * @description: login
   */
  @Action
  async login(params: LoginParams, goHome = true): Promise<GetUserInfoByUserIdModel | null> {
    try {
      const data = await loginApi(params);
      const { token } = data;
      // console.log(token);
      // get user info
      // const userInfo = await this.getUserInfoAction({ userId });

      // save token
      this.commitTokenState(token);

      // const name = FULL_PAGE_NOT_FOUND_ROUTE.name;
      // name && router.removeRoute(name);
      goHome &&
        (await router.push(PageEnum.BASE_HOME).then(() => {
          setTimeout(() => {
            appStore.commitPageLoadingState(false);
          }, 30);
        }));
      return null;
    } catch (error) {
      return null;
    }
  }

  @Action
  async getUserInfoAction({ userId }: GetUserInfoByUserIdParams) {
    const userInfo = await getUserInfoById({ userId });
    const { role } = userInfo;
    const roleList = [role.value] as RoleEnum[];
    this.commitUserInfoState(userInfo);
    this.commitRoleListState(roleList);
    return userInfo;
  }

  /**
   * @description: login out
   */
  @Action
  async loginOut(goLogin = false) {
    goLogin && router.push(PageEnum.BASE_LOGIN);
  }

  @Action
  async resumeAllState() {
    resetRouter();
    clearSession();
    clearLocal();
    // router.addRoute(FULL_PAGE_NOT_FOUND_ROUTE as RouteRecordRaw);
    permissionStore.commitResetState();
    tabStore.commitResetState();
    this.resetState();
  }

  /**
   * @description: Confirm before logging out
   */
  @Action
  async confirmLoginOut() {
    const { createConfirm } = useMessage();
    createConfirm({
      iconType: 'warning',
      title: '温馨提醒',
      content: '是否确认退出系统?',
      onOk: async () => {
        await this.loginOut(true);
      },
    });
  }
}
export { User };
export const userStore = getModule<User>(User);
