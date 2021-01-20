<template>
  <card v-for="post in store.state.posts">
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
  import { store } from './store';
  import Card from '../pokemon/Card.vue';
  import Controls from './Controls.vue';

  export default {
    components: {
      Controls,
      Card,
    },
    setup() {
      return {
        store,
      };
    },
  };
</script>

<style scoped></style>
