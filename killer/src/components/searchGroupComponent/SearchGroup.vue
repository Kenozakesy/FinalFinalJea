<template>
   <div>
      <p>Search group</p>
      <div class="shadow-none p-2 mb-3 bg-light rounded scroll">
         <ul v-for="(server, index) of servers" :key="index">
            <li>
               <PotientalServer
               :server="server"
               ></PotientalServer>
            </li>
         </ul>
      </div>
      <div class="shadow-none p-3 mb-5 bg-light rounded input-group-append">
         <input type="text" v-model="servername" id="input" placeholder="server name" class="form-control width">
         <button class="btn btn-primary" @click="search"><img src="../../assets/search3.png" width="20px" height="20px"></button>
      </div>
   </div>
</template>

<script>
import axios from 'axios'
import PotientalServer from './potientalServer'

export default {
  name: 'SearchGroup',
  components: { PotientalServer },
  data () {
    return {
      servername: '',
      servers: [],
      class: false
    }
  },
  methods: {
    search () {
      axios.get('http://localhost:8080/war/api/server/search/' + this.servername)
        .then((response) => {
          this.servers = response.data
          console.log(response.data)
        })
        .catch(error => {
          console.log(error.toString())
          this.servers = []
        })
    }
  }
}
</script>

<style scoped>
   .width{
      width: 180px;
      margin-right: 5px;
   }
   .color{
      border: 2px solid red;
   }
   ul {
      list-style-type: none;
      padding: 0px;
   }
   .scroll{
      overflow: auto;
      height: 500px;
   }
   .align{
      /*display: inline-block;*/
   }
</style>
