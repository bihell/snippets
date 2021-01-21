<template>
  <button v-for="post in posts" @click="fetchPostData(post.id)">
    {{ post.title }}
  </button>
  <div v-if="currentPost">
    <h1>{{ currentPost.title }}</h1>
    <p>{{ currentPost.content }}</p>
  </div>

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
      currentPost() {
        console.log(this.$store.state.currentPost);
        return this.$store.state.currentPost;
      },
    },
    methods: {
      increment() {
        this.$store.commit('increment', { number: Math.random() });
      },
      fetchPostData(id) {
        // mutation
        // this.$store.commit('setPostId', id);
        // action
        this.$store.dispatch('fetchDataFromServer', id);
      },
    },
  };
</script>

<style scoped></style>
