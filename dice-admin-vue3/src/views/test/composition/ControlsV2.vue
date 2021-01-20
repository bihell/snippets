<template>
  <div>
    <button>
      {{ post.likes }}
    </button>
    <Hashtag v-for="tag in post.hashtags" :hashtag="tag" />
  </div>
</template>

<script>
  import Hashtag from './HashtagV2.vue';
  export default {
    components: { Hashtag },
    props: {
      post: {
        type: Object,
      },
    },
  };
</script>

<style scoped></style>
