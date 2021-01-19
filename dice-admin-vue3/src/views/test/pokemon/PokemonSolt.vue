<template>
  <div class="cards">
    <Card v-for="starter in starters" @click="fetchEvolutions(starter)">
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

  <div class="cards">
    <Card v-for="creature in evolutions">
      <template #title>
        {{ creature.name }}
      </template>
      <template #content>
        <img :src="creature.sprite" />
      </template>
      <template #description>
        <div v-for="type in creature.types">
          {{ type }}
        </div>
      </template>
    </Card>
  </div>
</template>

<script>
  import Card from './Card.vue';

  const api = 'https://pokeapi.co/api/v2/pokemon';
  const STARTER_IDS = [1, 4, 7];

  export default {
    components: {
      Card,
    },
    data() {
      return {
        starters: [],
        evolutions: [],
      };
    },

    async created() {
      this.starters = await this.fetchData(STARTER_IDS);
    },

    methods: {
      async fetchEvolutions(pokemon) {
        this.evolutions = await this.fetchData([pokemon.id + 1, pokemon.id + 2]);
        console.log(this.evolutions);
      },

      async fetchData(ids) {
        // Promise.all 表示同时等待多个异步请求
        const responses = await Promise.all(ids.map((id) => window.fetch(`${api}/${id}`)));
        const data = await Promise.all(responses.map((res) => res.json()));
        return data.map((datum) => ({
          name: datum.name,
          sprite: datum.sprites.other['official-artwork'].front_default,
          types: datum.types.map((type) => type.type.name),
          id: datum.id,
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
