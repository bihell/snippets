<template>
  <div>
    <a-button @click="increment()"> Increment </a-button>
    <p>{{ count }}</p>
    <div v-if="isEven(count)"> Eve </div>
    <div v-else> Odd </div>
    <div v-for="number in evenList">
      <!--      这里也可以用v-show , 区别不会显示在dom里面-->
      {{ number }}
    </div>
    <a-divider />
    <!--    class 绑定-->
    <div v-for="number in numbers">
      <div :class="getClass(number)" :title="number">
        {{ number }}
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Test1',
    data: function () {
      return {
        count: 3,
        numbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      };
    },
    computed: {
      evenList() {
        // return this.numbers.filter((num) => {
        //   return this.isEven(num);
        // });
        //  可以做如下简化
        return this.numbers.filter(this.isEven);
      },
    },
    methods: {
      getClass(number) {
        if (this.isEven(number)) {
          return 'red';
        }
        return 'blue';
      },
      increment() {
        this.count += 1;
      },
      isEven(number) {
        return number % 2 === 0;
      },
    },
  };
</script>

<style scoped>
  .red {
    color: red;
  }

  .blue {
    color: blue;
  }
</style>
