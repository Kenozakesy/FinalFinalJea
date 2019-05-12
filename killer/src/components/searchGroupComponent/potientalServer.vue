<template>
   <div>
      <div class="shadow-sm p-3 mb-2 bg-white rounded" id="box">
         <b-row>
            <b-col cols="6" class="padding">
                  <p v-bind="server.id">{{server.name}}</p>
            </b-col>
            <b-col cols="6" >
               <b-dropdown size="sm" text="" class="m-2 dropmargin">
                  <b-dropdown-item-button @click="join">join</b-dropdown-item-button>
               </b-dropdown>
            </b-col>
         </b-row>
      </div>
   </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'potientalServer',
  props: {
    server: Object
    // updateName: Function
  },
  data () {
    return {
      user: {
        accountId: '',
        serverId: ''
      }
    }
  },
  methods: {
    join () { // TODO: update immediatly
      this.user.accountId = localStorage.getItem('userId')
      this.user.serverId = this.server.id
      axios.post('http://localhost:8080/war/api/accountServer', this.user, {
        headers: {
          'Content-Type': 'application/json; charset=utf-8',
          'Accept': 'application/json'
        }
      })
        .then((data) => {
          console.log(data.data)
          // this.$parent.loadPage()
        })
        .catch(error => {
          console.log(error)
          alert('you have already joined this group.')
        })
    }

  },
  mounted () {

  }
}
</script>

<style scoped>
   p{
      margin: 10px 0px 0px 0px;
      font-size: 16px;
   }
   #box{
      margin: 0px 5px 0px 0px;
   }
   .padding{

   }
   .dropmargin {
      padding-top: 0px;
   }
</style>
