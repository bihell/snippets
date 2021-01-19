<template>
  <div class="cards">
    <Card v-for="starter in starters">
      <template #title>
        {{ starter.name }}
      </template>
      <template #content>
        <img :src="starter.sprite" />
      </template>
      <template #description>
        <div v-for="type in starter.types">
          {{ type }}
        </div>
      </template>
    </Card>
  </div>
</template>

<script>
  import Card from './Card.vue';

  const api = 'https://pokeapi.co/api/v2/pokemon';
  const ids = [1, 4, 7];

  export default {
    components: {
      Card,
    },
    data() {
      return {
        starters: [],
      };
    },

    created() {
      this.fetchData();
    },

    methods: {
      async fetchData() {
        // Promise.all 表示同时等待多个异步请求
        const responses = await Promise.all(ids.map((id) => window.fetch(`${api}/${id}`)));
        const data = await Promise.all(responses.map((res) => res.json()));
        this.starters = data.map((datum) => ({
          name: datum.name,
          sprite: datum.sprites.other['official-artwork'].front_default,
          types: datum.types.map((type) => type.type.name),
        }));
      },
    },
  };
</script>

<style scoped>
  .cards {
    display: flex;
  }

  img {
    width: 100%;
  }
</style>
