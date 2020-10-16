var app = new Vue({
 el: '#app',
 data: {
  inputWidth: 60,
  sliderStatus: false,
  labelArr: ['font-weight-bold', 'mr-2'],
  maximum: 99,
  products: null,
  cart: []
 },
 filters: {
  currency: function(value) {
   return '$' + Number.parseFloat(value).toFixed(2);
  }
 },
 computed: {
  cartTotal: function() {
   let sum = 0;
   for (key in this.cart) {
    sum = sum+(this.cart[key].product.price * this.cart[key].qty);
   }
   return sum;
  },
  cartQty: function() {
   let qty = 0;
   for (key in this.cart) {
    qty = qty + this.cart[key].qty;
   }
   return qty;
  },
  sliderState: function() {
   return this.sliderStatus ? 'd-flex': 'd-none'
  }
 },
 methods: {
  beforeEnter: function(el) {
   el.className='d-none'
  },
  enter: function(el) {
   var delay=el.dataset.index * 100;
   setTimeout(function() {
    el.className='row d-flex mb-3 align-items-center animated fadeInRight'
   }, delay);
  },
  leave: function(el) {
   var delay=el.dataset.index * 100;
   setTimeout(function() {
    el.className='row d-flex mb-3 align-items-center animated fadeOutRight'
   }, delay);
  },  
  addItem: function(product) {
   var whichProduct;
   var existing = this.cart.filter(function(item, index) {
    if (item.product.id == Number(product.id)) {
     whichProduct = index;
     return true;
    } else {
     return false;
    }
   });
   
   if (existing.length) {
    this.cart[whichProduct].qty++
   } else {
    this.cart.push({product: product, qty: 1})
   }
   
   
  },
  deleteItem: function(id) {
   if(this.cart[id].qty>1) {
    this.cart[id].qty--;
   } else {
    this.cart.splice(id, 1);
   }
  }
 },
 mounted: function() {
  fetch('https://hplussport.com/api/products/order/price')
  .then(response => response.json())
  .then(data => {
    this.products = data;
  })
 }
});