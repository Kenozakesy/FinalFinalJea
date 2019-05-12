<template>
   <div>
      <p>friendlist</p>
      <div class="shadow-none p-2 mb-3 bg-light rounded scroll">
         <ul v-for="(friend, index) of friends" :key="index">
            <li>
               <Friend></Friend>
            </li>
         </ul>
      </div>
   </div>
</template>

<script>
import Friend from './Friend'
export default {
  name: 'Friendlist',
  components: {
    Friend
  },
  data () {
    return {
      friends: ['friend', 'friend', 'friend', 'friend', 'friend', 'friend', 'friend']
    }
  },
  created () {

  }
}
</script>

<style scoped>

.margin{
   padding: 10px 0px 0px 0px;
}
ul {
   list-style-type: none;
   padding: 0px;
}
.scroll{
   overflow: auto;
   height: 500px;
}

</style>
