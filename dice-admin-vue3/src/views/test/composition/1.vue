<template>
  <button @click="increment">
    {{ count }}
  </button>
  <button @click="increase('a')">
    {{ numbers.a }}
  </button>
  <button @click="increase('b')">
    {{ numbers.b }}
  </button>
</template>

<script>
  import { ref, reactive } from 'vue';

  export default {
    setup() {
      // ref 比较适合单个值
      const count = ref(0);

      // reactive 比较适合复杂值，对象之类
      const numbers = reactive({
        a: 0,
        b: 0,
      });

      const increase = (num) => {
        numbers[num] += 1;
      };
      const increment = () => {
        count.value += 1;
      };

      return {
        increment,
        count,
        numbers,
        increase,
      };
    },
  };
</script>

<style scoped></style>
