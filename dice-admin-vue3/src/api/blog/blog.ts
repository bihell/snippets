import { defHttp } from '/@/utils/http/axios';
import { ArticleListParams, ArticleListGetResultModel } from './model/blogModel';

enum Api {
  Article_LIST = '/article',
}

/**
 * @description: Get sample list value
 */
export function articleListApi(params: ArticleListParams) {
  return defHttp.request<ArticleListGetResultModel>({
    url: Api.Article_LIST,
    method: 'GET',
    params,
    headers: {
      ignoreCancelToken: true,
    },
  });
}
