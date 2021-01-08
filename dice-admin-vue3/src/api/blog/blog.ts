import { defHttp } from '/@/utils/http/axios';
import {
  ArticleListParams,
  ArticleListGetResultModel,
  OptionsGetResultModel,
  OptionsParams,
  PostParams,
  PostItem,
} from './model/blogModel';

enum Api {
  ARTICLE_LIST = '/article/getPageList',
  POST = '/article/',
  META_LIST = '/meta/meta_list',
}

export function apiGetPost(params: PostParams) {
  return defHttp.request<PostItem>({
    url: Api.POST + params.id,
    method: 'GET',
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
