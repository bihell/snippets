import { BasicPageParams, BasicFetchResult } from '/@/api/model/baseModel';
/**
 * @description: Request list interface parameters
 */
export type ArticleListParams = BasicPageParams;

export interface ArticleListItem {
  createTime: string;
  updateTime: string;
  creator: number;
  modifier: number;
  deleted: number;
  id: number;
  title: string;
  content: string;
  hits: number;
  tags: string;
  category: string;
  priority: number;
  status: string;
  type: string;
  allowComment: boolean;
  commentCount: number;
}

export interface OptionsParams {
  type: string;
}

/**
 * @description: Request list return value
 */
export type ArticleListGetResultModel = BasicFetchResult<ArticleListItem>;

export interface OptionsItem {
  label: string;
  value: string;
}

/**
 * @description: Request list return value
 */
export type OptionsGetResultModel = BasicFetchResult<OptionsItem[]>;
