import { defHttp } from '/@/utils/http/axios';
import { ArticleListParams, ArticleListGetResultModel } from './model/blogModel';

enum Api {
  Article_LIST = '/admin/article',
}

/**
 * @description: Get sample list value
 */
export function demoListApi(params: ArticleListParams) {
  return defHttp.request<ArticleListGetResultModel>({
    url: Api.Article_LIST,
    method: 'GET',
    params,
    headers: {
      ignoreCancelToken: true,
    },
  });
}
