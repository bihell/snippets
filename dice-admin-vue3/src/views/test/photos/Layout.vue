<template>
  <div class="wrapper">
    <div class="header">
      <slot name="header" />
    </div>

    <div class="main">
      <slot name="sidebar" />
      <slot name="content" />
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Layout',
  };
</script>

<style scoped></style>
