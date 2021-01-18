<template>
  <MyInput
    name="Username"
    :value="username.value"
    :rules="{ required: true, min: 5 }"
    type="text"
    @update="update"
  />
  <MyInput
    name="Password"
    :value="password.value"
    :rules="{ required: true, min: 10 }"
    type="password"
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
    computed: {
      valid() {
        return this.username.valid && this.password.valid;
      },
    },
    methods: {
      update(payload) {
        console.log(payload);
        this[payload.name.toLowerCase()] = {
          valid: payload.valid,
          value: payload.value,
        };
      },
    },
  };
</script>

<style scoped>
  body {
    font-family: Arial;
  }
</style>
