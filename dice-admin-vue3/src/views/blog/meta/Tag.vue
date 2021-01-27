<template>
  <div class="app-container">
    <a-row :gutter="30">
      <a-col :xs="24" :sm="12" :md="12" :lg="12">
        <a-card title="标签列表">
          <ul class="meta-list">
            <li v-for="tag in tagList" :key="tag.value">
              <span class="meta" @click="handleTagClick(tag)">{{ tag.label }} </span>
              <span style="float: right; clear: both;">
                <span class="radius-count">{{ tag.count }}</span>
                <a-button type="danger" @click="handleDeleteTagClick(tag.label)">删除</a-button>
              </span>
            </li>
          </ul>
          <a-input v-model:value.trim="tag.name" placeholder="请输入标签名称" class="meta-input" />
          <a-button style="float: right; clear: both;" @click="handleSaveOrUpdateTagClick">
            保存标签
          </a-button>
        </a-card>
      </a-col>
      <a-col :xs="24" :sm="12" :md="12" :lg="12">
        <a-card title="分类列表">
          <p>card content</p>
          <p>card content</p>
          <p>card content</p>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script lang="ts">
  import { store } from '../store';
  import { computed, reactive } from 'vue';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { apiSaveMeta } from '/@/api/blog/blog';
  const { createMessage, createConfirm, createErrorModal } = useMessage();
  const { success } = createMessage;
  export default {
    setup() {
      store.fetchMetaList();
      const tag = reactive({
        name: '',
        id: '',
      });

      const setTag = (v) => {
        tag.name = v.label;
        tag.id = v.value;
      };

      function handleTagClick(v) {
        setTag(v);
      }

      function handleDeleteTagClick(v) {
        console.log(v);
      }

      async function handleSaveOrUpdateTagClick() {
        console.log(tag.name === null);
        console.log(tag.id === null);
        console.log(tag);
        if (tag.name === '') {
          createErrorModal({ title: 'Tip', content: '标签名不能为空' });
          return;
        }
        if (tag.id !== '') {
          success('更新标签成功');
          setTag({ label: '', value: '' });
          await store.fetchMetaList();
        } else {
          await apiSaveMeta(tag.name, 'tag');
          success('新增标签成功');
          setTag({ label: '', value: '' });
          await store.fetchMetaList();
        }
      }
      return {
        tagList: computed(() => store.state.tagList),
        categoryList: computed(() => store.state.categoryList),
        tag,
        handleTagClick,
        handleDeleteTagClick,
        handleSaveOrUpdateTagClick,
      };
    },
  };
</script>

<style scoped>
  .app-container {
    padding: 6px;
  }

  .meta-list {
    padding: 0;
    margin: 0 0 30px 0;
    list-style: none;
  }

  .meta-list li {
    line-height: 2.4rem;
  }

  .meta-list .meta {
    max-width: 350px;
    padding: 0.4rem 0.5rem;
    margin: 0.4rem;
    font-size: 14px;
    font-weight: 600;
    color: #333;
    white-space: nowrap;
    cursor: pointer;
    background-color: #ffd740;
    border: 1px solid #ffd740;
    box-shadow: 0 0 3px rgba(14, 14, 14, 0.3);
  }

  .meta-list .meta:hover {
    box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
    transition: all 0.2s;
  }

  .meta-list .meta:active {
    box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
  }

  .meta-input {
    display: inline-block;
    width: 200px;
    margin-left: 5px;
  }

  .clearfix::before,
  .clearfix::after {
    display: table;
    content: '';
  }

  .clearfix::after {
    clear: both;
  }
</style>
