<template>
   <div>
      <div class="shadow-sm p-3 mb-2 bg-white rounded" id="box">
         <b-row>
            <b-col cols="6" class="padding">
               <img src="../../assets/discord-512.png" width="50" height="50">
               <router-link :to="'/server/' + group.id" @click.native="updateName(group.name, group.id)">
                  <p v-bind="group.id">{{group.name}}</p>
               </router-link>
            </b-col>
            <b-col cols="5" >
               <b-dropdown size="sm" text="" class="m-2 dropmargin">
                  <b-dropdown-item-button class="align" @click="deleteGroup">delete</b-dropdown-item-button>
                  <b-dropdown-item-button class="align" @click="$modal.show('change-channel-modal' , { channel : channel })">change</b-dropdown-item-button>
               </b-dropdown>
            </b-col>
         </b-row>
      </div>
   </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Group',
  props: {
    group: Object,
    updateName: Function
  },
  methods: {
    deleteGroup () {
      axios.delete('http://localhost:8080/war/api/server/' + this.group.id)
        .then((response) => {
          this.$parent.reCreate(this.group.id)
          // this.$parent.urlRefresh(this.group.id)
        })
        .catch(error => {
          console.log(error.toString())
        })
    }
  },
  mounted () {

  }
}
</script>

<style scoped>
p{
   margin: 5px;
   font-size: 11px;
}
#box{
   margin: 0px 5px 0px 0px;
}
.padding{
   padding-right: 10px;
}
.dropmargin {
   padding-top: 10px;
}
.align{
   font-size: 12px;
}
</style>
