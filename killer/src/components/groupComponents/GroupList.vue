<template>
   <div class="div">
      <p v-if="activeServer.activeServername === '' || null">_____________</p>
      <p v-if="activeServer.activeServername">{{this.activeServer.activeServername}}</p>
      <div class="shadow-none p-2 mb-3 bg-light rounded scroll">
         <ul v-for="(group, index) of groups" :key="index">
            <li  >
               <Group
                 :group="group"
                 :updateName="updateName"
               ></Group>
            </li>
         </ul>
      </div>

      <div class="shadow-none p-3 mb-5 bg-light rounded">
         <button class="btn btn-primary" @click="$modal.show('create-server-modal')">Create Server</button>
      </div>

      <create-server-modal></create-server-modal>
   </div>
</template>

<script>
/* eslint-disable */
import Group from './Group'
import virtualList from 'vue-virtual-scroll-list'
import axios from 'axios'

export default {
  name: 'GroupList',
  props: {
    GroupId: String
  },
  components: {
    Group,
    virtualList
  },
  data() {
    return {
      groups: [],
      id: null,
      activeServer: {
        activeServername: '',
        activeServerId: ''
      }
    }
  },
  methods: {
    updateName (name, id){
      this.activeServer.activeServername = name
      this.activeServer.activeServerId = id
    },
    urlRefresh (groupid) {
      // check if current url is equal to the deleted one
      console.log('array: ' + this.groups.length)
      if(this.groups.length === 0) {
        console.log('into')
        this.$router.push('/home')
      }
      else if(groupid === this.activeServer.activeServerId) {
        if(this.groups.length > 0) {
          let id = this.groups[0].id
          let name = this.groups[0].name
          this.$router.push('/server/' + id)
          this.updateName(name, id)
        }
      }
      // else do nothing
    },
    create() {
      axios.get('http://localhost:8080/war/api/server/user/' + localStorage.getItem('userId'), {})
        .then((response) => {
          console.log(response.data)
          this.groups = response.data;
        })
        .catch(error => {
          console.log(error.toString())
        })
    },
    reCreate(groupId) {
      axios.get('http://localhost:8080/war/api/server/user/' + localStorage.getItem('userId'), {})
        .then((response) => {
          console.log(response.data)
          this.groups = response.data;
          this.urlRefresh(groupId)
        })
        .catch(error => {
          console.log(error.toString())
        })
    },
    getServername() {
      console.log('group')
      axios.get('http://localhost:8080/war/api/server/' + this.GroupId, {})
        .then((response) => {
          console.log(response.data)
          var server = response.data
          this.activeServer.activeServername = server.name
          this.activeServer.activeServerId = server.id
        })
        .catch(error => {
          console.log(error.toString())
        })
    }
  },
  created () {
      this.create()
      this.getServername()
  }
}
</script>

<style scoped>
   .div{
      height: border-box;
      width: border-box;
   }
   #listItem{
      height: 100px;
   }
   .margin{
      padding: 10px 0px 0px 5px;
   }
   button {
      font-size: 15px;
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
