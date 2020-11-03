<template>
  <button
      :style="{ background,color}"
      :class="{'disabled-button':disabled}"
  >
Button
  </button>
</template>

<script>
export default {
  props:{
    color:{
      type: String
    },
    background: {
      type: String
    },
    disabled:{
      type: Boolean
    }
  }
}
</script>

<style scoped>
.disabled-button {
  opacity: 0.5;
}
button{
  background: none;
  color: black;
  border: none;
  border-radius: 5px;
  padding: 10px 40px;
  font-size: 16px;
  cursor: pointer;
}

button:hover{
  filter: brightness(125%);
}

</style>
