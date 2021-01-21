<template>
  Count: {{ count }}
  <button @click="increment"> Increment </button>
</template>

<script>
  export default {
    computed: {
      count() {
        return this.$store.state.count;
      },
    },
    methods: {
      increment() {
        this.$store.commit('increment', { number: Math.random() });
      },
    },
  };
</script>

<style scoped></style>
