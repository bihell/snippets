<template>
  {{ post }}
</template>

<script>
  export default {
    props: {
      post: {
        type: Object,
      },
    },

    setup(props) {
      console.log(props.post.title);
    },
  };
</script>

<style scoped></style>
