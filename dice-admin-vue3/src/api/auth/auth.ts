import { defHttp } from '/@/utils/http/axios';

enum Api {
  USER_LIST = '/auth/user/list',
  API_LIST = '/auth/api/list',
}

export function apiUserList(params: any) {
  return defHttp.request({
    url: Api.USER_LIST,
    method: 'POST',
    params,
  });
}

export function apiApiList(params: any) {
  return defHttp.request({
    url: Api.API_LIST,
    method: 'POST',
    params,
  });
}
