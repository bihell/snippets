<template>
  <button v-for="post in posts" @click="fetchPostData(post.id)">
    {{ post.title }}
  </button>

  Current Post Id: {{ currentPostId }}

  Count: {{ count }}
  <button @click="increment"> Increment </button>
</template>

<script>
  export default {
    data() {
      return {
        posts: [
          {
            id: 1,
            title: 'Post #1',
          },
          {
            id: 2,
            title: 'Post #1',
          },
        ],
      };
    },
    computed: {
      count() {
        return this.$store.state.count;
      },
      currentPostId() {
        return this.$store.state.currentPostId;
      },
    },
    methods: {
      increment() {
        this.$store.commit('increment', { number: Math.random() });
      },
      fetchPostData(id) {
        this.$store.commit('setPostId', id);
      },
    },
  };
</script>

<style scoped></style>
