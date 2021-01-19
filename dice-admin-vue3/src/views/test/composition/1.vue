<template>
  <button @click="increment">
    {{ count }}
  </button>
</template>

<script>
  import { ref, reactive } from 'vue';

  export default {
    setup() {
      const count = ref(0);
      const increment = () => {
        count.value += 1;
      };

      return {
        increment,
        count,
      };
    },
  };
</script>

<style scoped></style>
