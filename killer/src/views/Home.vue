
<template>
   <div id="app">
      <Header></Header>

      <b-container id="container">
         <b-row>
            <b-col cols="2">
               <GroupList></GroupList>

            </b-col>
            <b-col cols="7">
               <!--<Chatbox></Chatbox>-->
               <ChatboxPlaceholder></ChatboxPlaceholder>
            </b-col>
            <b-col cols="3">
               <!--<Friendlist></Friendlist>-->
               <SearchGroup></SearchGroup>
            </b-col>
         </b-row>
      </b-container>

   </div>
</template>

<script>
/* eslint-disable */
import { mapActions } from 'vuex'
import Header from '../components/Header'
import GroupList from '../components/groupComponents/GroupList'
import Chatbox from '../components/chatboxComponent/Chatbox'
import Friendlist from '../components/friends/Friendlist'
import ChatboxPlaceholder from '../components/chatboxComponent/ChatboxPlaceholder'
import SearchGroup from '../components/searchGroupComponent/SearchGroup'

export default {
  name: 'Home',
  components: {
    SearchGroup,
    ChatboxPlaceholder,
      Friendlist,
      Header,
      GroupList,
      Chatbox
  },
  methods: {
    ...mapActions([
      'logout'
    ])
  }
}
</script>

<style scoped>
   #app {
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
      margin-top: 80px;
   }
   #container {
      margin-top: 20px;
   }
</style>
