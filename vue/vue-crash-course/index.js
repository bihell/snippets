import { createApp } from 'vue/dist/vue.esm-browser.js'

const app = createApp({
   template:`
   <button @click="increment(5)">Increment</button>
   <p>{{ count}}</p>

   <div v-if="isEven(count)">
      Even
   </div>
   <div v-else>
    Odd
    </div>

    <div v-for="number in numbers">
    <div :class="getClass(number)" :title="number">
      {{ number }}
    </div>
    </div>
   
   <input @input="input"/>
   `,
   computed:{
     evenList(){
       return this.numbers.filter(this.isEven)
     }
   },
  data(){
    return{
      count:0,
      numbers:[1,2,3,4,5,6,7,8,9,10]
    }
  },
  methods:{
     input(){
       console.log('...')
     },
     getClass(number){
       if(this.isEven(number)){
         return 'red'
       }
       return 'blue'
     },
    increment(val){
      this.count += val
    },
    isEven(number){
      return number % 2 === 0
    }
  }
}).mount('#app')

window.app = app
