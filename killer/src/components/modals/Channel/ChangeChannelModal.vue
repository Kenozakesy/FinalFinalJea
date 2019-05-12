<template>
   <modal name="change-channel-modal"
          id="modal-test"
          :adaptive="true"
          :scrollable="false"
          :max-width=600
          height="100%"
          @before-open="beforeOpen"
          @before-close="beforeClose">
      <form id="scroll" class="overflow-y-auto p-8 center w-full h-full" @submit.prevent="change()">
         <div class="center w-full flex-col">

            <h2 align="center">Change name</h2>

            <p v-if="this.Error !== ''" class="error" align="center">{{ Error }}</p>

            <div class="w-full">
               <div class="flex flex-col p-4">
                  <input class="form-control"
                         v-model="channel.name"
                         type="text"
                         placeholder="New channel name">

                  <button class="btn btn-primary margin" >
                     Change name
                  </button>
               </div>
            </div>
         </div>
      </form>

   </modal>
</template>

<script>
import axios from 'axios'

export default {
  name: 'change-channel-modal',
  data () {
    return {
      channel: {
        id: null,
        name: '',
        serverId: ''
      },
      Error: ''
    }
  },
  methods: {
    beforeOpen (e) {
      this.channel = e.params.channel
    },
    beforeClose () {
      this.Error = ''
      // this.channel.name = ''
    },
    change () {
      this.Error = ''
      console.log(this.channel.serverId)
      if (!this.channel.name) {
        this.Error = 'Not a valid channel name'
      } else {
        axios.put('http://localhost:8080/war/api/channel', this.channel, {
          headers: {
            'Content-Type': 'application/json; charset=utf-8',
            'Accept': 'application/json'
          }
        })
          .then((data) => {
            console.log(data.data)
            // this.$parent.refresh()
          })
          .catch(error => {
            console.log(error)
          })
      }
    }
  }
}
</script>

<style scoped>

   .error {
      color: red;
      font-weight: bold;
      margin: 0px;
   }
   .margin{
      margin-top: 10px;
   }

</style>
