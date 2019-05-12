<template>
   <div>
      <p v-if="activeChannel.activeChannelName === '' || null">_____________</p>
      <p v-if="activeChannel.activeChannelName">{{this.activeChannel.activeChannelName}}</p>

      <div class="shadow-none p-2 mb-3 bg-light rounded scroll">
         <ul>
            <li v-for="(channel, index) of channels" :key="index" >
               <Channel
              :channel="channel"
              :updateName="updateName"
               ></Channel>
            </li>
         </ul>
      </div>

      <div class="shadow-none p-3 mb-5 bg-light rounded">
         <button class="btn btn-primary" @click="$modal.show('create-channel-modal' , { serverId: server.id })">Create channel</button>
      </div>
      <create-channel-modal></create-channel-modal>
   </div>
</template>

<script>
/* eslint-disable */
import Channel from './Channel'
import virtualList from 'vue-virtual-scroll-list'
import axios from 'axios'

export default {
  name: 'ChannelList',
  props: {
    serverId: String,
    channelId: String
  },
  components: {
    Channel,
    virtualList
  },
  watch: {
    serverId: function (id) {
      if (id === undefined){
        this.server.id = 0
      } else {
        this.server.id = id
      }
      this.loadPage()
    },
    channelId: function (id) {
      if (id === undefined){
        this.activeChannel.activeChannelId = 0
        this.activeChannel.activeChannelName = ''
      } else {
        // this.server.id = id
      }
      // this.loadPage()
    },
    channels: {
    }
  },
  data() {
    return {
      channels: [],
      server: {
        id: this.serverId,
      },
      activeChannel: {
        activeChannelName: '',
        activeChannelId: ''
      }
    }
  },
  methods: {
    updateName (name, id) {
      this.activeChannel.activeChannelName = name
      this.activeChannel.activeChannelId = id
    },
    urlRefresh (channelid) {
      // check if current url is equal to the deleted one
      console.log('array: ' + this.channels.length)
      if(this.channels.length === 0) {
        this.$router.push('/server/' + this.server.id)
      }
      else if(channelid === this.activeChannel.activeChannelId) {
        if(this.channels.length > 0) {
          let id = this.channels[0].id
          let name = this.channels[0].name
          this.$router.push('/server/' + this.server.id + '/channel/' + id)
          this.updateName(name, id)
        }
      }
      // else do nothing
    },
    loadPage() {
     console.log(this.server.id)
     axios.get('http://localhost:8080/war/api/channel/server/' + this.server.id)
       .then((response) => {
         console.log(response.data)
         this.channels = response.data
       })
       .catch(error => {
         console.log(error.toString())
       })
    },
    reLoadPage(channelId) {
      console.log(this.server.id)
      axios.get('http://localhost:8080/war/api/channel/server/' + this.server.id)
        .then((response) => {
          console.log(response.data)
          this.channels = response.data
          this.urlRefresh(channelId)
        })
        .catch(error => {
          console.log(error.toString())
        })
    }
  },
  created () {
     this.loadPage()
  }
}
</script>

<style scoped>
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
