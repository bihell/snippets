<template>
  <MyInput name="Username" :rules="{ required: true, min: 5 }" />
  <MyInput name="Password" :rules="{ required: true, min: 10 }" />
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
      };
    },
  };
</script>

<style scoped>
  body {
    font-family: Arial;
  }
</style>
