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
