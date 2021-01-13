<template>
  <div>
    <num v-for="asdf in numbers" :number="asdf" />
  </div>
</template>

<script>
  import Num from './Num.vue';
  export default {
    name: 'Test1',
    components: { Num },
    data: function () {
      return {
        count: 3,
        numbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        value: ['a'],
        user: {
          username: '路飞',
          password: '123456',
        },
      };
    },
    computed: {
      error() {
        if (this.value.length < 7) {
          return 'Too short';
        }
      },
    },
    methods: {
      fun() {
        alert(this.user.username + '   ' + this.user.password);
        this.user.username = '佐助';
        this.user.password = '666666';
      },

      // 如果什么变量都不输入，那么默认传入的是event参数，参数名随意
      input($evt) {
        this.value = $evt.target.value;
      },
      increment() {
        this.count += 1;
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
