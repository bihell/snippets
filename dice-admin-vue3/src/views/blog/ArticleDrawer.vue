<template>
  <BasicDrawer v-bind="$attrs" title="文章设置" width="30%" show-footer>
    <template #footer>
      <a-button> customerFooter</a-button>
    </template>
  </BasicDrawer>
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { BasicDrawer } from '/@/components/Drawer';
  export default defineComponent({
    components: { BasicDrawer },
    setup() {
      return {
        handleOk: () => {
          console.log('=====================');
          console.log('ok');
          console.log('======================');
        },
      };
    },
  });
</script>
