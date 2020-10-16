var app = new Vue({
 el: '#app',
 data: {
  slugText: 'The Quick #&#$^ Brown'
 },
 
 computed: {
  beginTime: function() {
   var date = new Date();
   return (
    String(date.getHours()) +
    String(date.getMinutes()) +
    String(date.getSeconds())
   )
  },
  slugetize: function() {
    return this.slugText
    .toLowerCase()
    .replace(/[^\w ]+/g, '')
    .replace(/ +/g, '-') + 'begin time:' + this.beginTime + 'end time:' + this.endTime()
  }
 },
 methods:{
   endTime: function() {
   var date = new Date();
   return (
    String(date.getHours()) +
    String(date.getMinutes()) +
    String(date.getSeconds())
   )
   }
 }
 
});