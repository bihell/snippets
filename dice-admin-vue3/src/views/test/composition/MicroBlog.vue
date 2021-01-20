<template>
  <card v-for="post in store.state.posts">
    <template #title>
      {{ post.title }}
    </template>
    <template #content>
      {{ post.content }}
    </template>
    <template #description>
      <Controls :post="post" @setHashtag="setHashtag" />
    </template>
  </card>
  {{ currentHashtag }}
</template>

<script>
  import { ref } from 'vue';
  import { store } from './store';
  import Card from '../pokemon/Card.vue';
  import Controls from './Controls.vue';

  export default {
    components: {
      Controls,
      Card,
    },
    setup() {
      const currentHashtag = ref();
      const setHashtag = (tag) => {
        currentHashtag.value = tag;
      };
      return {
        store,
        setHashtag,
        currentHashtag,
      };
    },
  };
</script>

<style scoped></style>
