<template>
  <BasicDrawer v-bind="$attrs" title="文章设置" width="20%" show-footer @register="register">
    <a-form layout="vertical">
      <a-form-item label="标签">
        <a-select
          v-model:value="tags"
          mode="multiple"
          style="width: 100%;"
          placeholder="请选择标签"
          @change="setTags"
        >
          <a-select-option v-for="tag in tagList" :key="tag.label">
            {{ tag.label }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="分类">
        <a-select
          v-model:value="category"
          style="width: 100%;"
          placeholder="请选择分类"
          @change="setCategory"
        >
          <a-select-option v-for="category in categoryList" :key="category.label">
            {{ category.label }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item layout="horizontal" label="是否置顶">
        <a-switch
          v-model:checked="priority"
          checked-children="是"
          un-checked-children="否"
          default-checked
          @change="setPriority"
        />
      </a-form-item>
    </a-form>

    <template #footer>
      <a-button class="mr-2" type="dashed"> 保存草稿 </a-button>
      <a-button class="mr-2" type="primary" @click="getFormValues"> 发布 </a-button>
      <a-button>发布并查看</a-button>
    </template>
  </BasicDrawer>
</template>
<script>
  import { computed, defineComponent } from 'vue';
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';
  import 'moment/dist/locale/zh-cn';
  import { store } from '/@/views/blog/post/store';

  export default defineComponent({
    components: { BasicDrawer },
    setup() {
      const [register] = useDrawerInner();

      function setTags(v) {
        store.setTags(v);
      }

      function setCategory(v) {
        store.setCategory(v);
      }

      function setPriority(v) {
        store.setPriority(v);
      }

      store.fetchMetaList();

      return {
        register,
        tags: computed(() =>
          store.state.currentPost.tags.length === 0 ? [] : store.state.currentPost.tags.split(',')
        ),
        category: computed(() => store.state.currentPost.category),
        tagList: computed(() => store.state.tagList),
        categoryList: computed(() => store.state.categoryList),
        priority: computed(() => store.state.currentPost.priority),
        setTags,
        setCategory,
        setPriority,
      };
    },
  });
</script>
