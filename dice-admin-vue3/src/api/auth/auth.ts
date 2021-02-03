import { defHttp } from '/@/utils/http/axios';
import { ArticleListGetResultModel, ArticleListParams } from '/@/api/blog/model/blogModel';

enum Api {
  USER_LIST = '/auth/user/list/all',
}

export function apiUserList(params: ArticleListParams) {
  return defHttp.request<ArticleListGetResultModel>({
    url: Api.USER_LIST,
    method: 'POST',
    params,
  });
}
