import { reactive } from 'vue';

class Store {
  constructor() {
    this.state = reactive({
      posts: [
        {
          id: 1,
          title: 'Learning Vue.js 3  ',
          content: 'tUsing the composition API',
          likes: 10,
          hashtags: ['vue', 'javascript', 'composition api'],
        },
      ],
    });
  }
}

export const store = new Store();
