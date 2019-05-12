<template>
   <div>
      <p>chatbox </p>

      <div class="shadow-none p-2 mb-3 bg-light rounded scroll">
         <ul v-for="(message, index) of messages" :key="index">
            <li>
               <Message
                 :message="message"
               ></Message>
            </li>
         </ul>
      </div>

      <div id="text">
         <textarea class="form-control" aria-label="With textarea" v-model="text" placeholder="message"></textarea>
         <button type="submit" class="btn btn-primary" id="send" @click="sendMessage">Send</button>
      </div>
   </div>
</template>

<script>
import Message from './Message'
import axios from 'axios'
// import $ from 'jQuery'

export default {
  name: 'Chatbox',
  props: [
    'channelId'
  ],
  components: {
    Message
  },
  watch: {
    'channelId': function (channelId) {
      if (!channelId) {
        this.messages = []
        this.id = null
      } else {
        this.id = this.channelId
        this.loadPage()
        this.connect()
      }
    }
  },
  data () {
    return {
      id: this.channelId,
      messages: [],
      ws: null,
      text: ''
    }
  },
  methods: {
    loadPage () {
      axios.get('http://localhost:8080/war/api/post/channel/' + this.channelId, {})
        .then((response) => {
          this.messages = response.data
          console.log(response.data)
        })
        .catch(error => {
          console.log('chatbox ' + error.toString())
        })
    },
    connect () {
      if (this.ws) {
        console.log('exist')
        this.ws.onclose = function () {} // disable onclose handler first
        this.ws.close()
      }
      let username = localStorage.getItem('username')
      // ws = new WebSocket('ws://' + 'localhost:9009' + pathname + '/chat/' + username)
      this.ws = new WebSocket('ws://' + 'localhost:8080/war/chat/' + this.id + '/' + username)

      this.ws.onmessage = (event) => {
        let message = JSON.parse(event.data)

        if (message.to === this.id) {
          this.messages.push(message)
          console.log(message.sender)
        }
      }
    },
    sendMessage () {
      var json = JSON.stringify({
        'message': this.text,
        'to': this.id
      })
      this.ws.send(json)
      this.text = ''
    }
  },
  created () {
    this.loadPage()
  }

}
</script>

<style scoped>
#send{
   margin: 10px;
}
#text{

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
