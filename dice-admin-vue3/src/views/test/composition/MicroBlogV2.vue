<template>
  <card v-for="post in filteredPosts">
    <template #title>
      {{ post.title }}
    </template>
    <template #content>
      {{ post.content }}
    </template>
    <template #description>
      <Controls :post="post" />
    </template>
  </card>
</template>

<script>
  import { computed } from 'vue';
  import { store } from './StoreV2';
  import Card from '../pokemon/Card.vue';
  import Controls from './ControlsV2.vue';

  export default {
    components: {
      Controls,
      Card,
    },
    setup() {
      const filteredPosts = computed(() => {
        if (!store.state.currentHashtag) {
          return store.state.posts;
        }
        return store.state.posts.filter((post) => {
          return post.hashtags.includes(store.state.currentHashtag);
        });
      });

      return {
        filteredPosts,
      };
    },
  };
</script>

<style scoped></style>
