<template>
  <div :class="getClass(number)">
    {{ number }}
  </div>
</template>

<script>
  export default {
    props: {
      number: {
        type: Number,
        required: true,
      },
    },
    methods: {
      getClass(number) {
        if (this.isEven(number)) {
          return 'red';
        }
        return 'blue';
      },
      isEven(number) {
        return number % 2 === 0;
      },
    },
  };
</script>

<style scoped></style>
