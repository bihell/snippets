<template>
  {{ username }}
  <MyInput
    name="Username"
    :value="username.value"
    :rules="{ required: true, min: 5 }"
    @update="update"
  />
  <MyInput
    name="Password"
    :value="password.value"
    :rules="{ required: true, min: 10 }"
    @update="update"
  />
  <MyButton color="white" background="darkslateblue" :disabled="!valid" />
  <!--  也可以是<my-button>-->
</template>

<script>
  import MyButton from './MyButton.vue';
  import MyInput from './MyInput.vue';
  export default {
    components: { MyInput, MyButton },
    data() {
      return {
        valid: true,
        username: {
          value: '',
          valid: false,
        },
        password: {
          value: '',
          valid: false,
        },
      };
    },
    methods: {
      update(payload) {
        console.log(payload);
        this[payload.name.toLowerCase()].value = payload.value;
      },
    },
  };
</script>

<style scoped>
  body {
    font-family: Arial;
  }
</style>
