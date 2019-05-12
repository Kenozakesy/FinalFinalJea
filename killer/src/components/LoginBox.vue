<template>
   <div>
     <h2 id="login">Login</h2>
      <p v-if="this.error !== ''" id="error">{{ error }}</p>
     <form @submit.prevent="LoginEmail">
       <input type="text" v-model="form.username" placeholder="username" class="form-control"><br>
       <input type="password" v-model="form.password" placeholder="password" class="form-control">
       <button class="btn btn-primary">Login</button>
     </form>
      <button @click="registerViewPush" class="btn btn-primary">Register</button>
      <two-factor-modal></two-factor-modal>
   </div>
</template>

<script>
/* eslint-disable */
import router from '../router'
import axios from 'axios'
import { mapState, mapActions } from 'vuex';

export default {
  name: 'LoginBox',
  components: {
  },
  data() {
    return {
      form: {
        username: '',
        password: ''
      },
      token: '',
      error: ''
    }
  },
  computed: {
    ...mapState([
      'loggingIn',
      'loginError',
    ])
  },
  methods: {
    ...mapActions([
      'doLogin'
    ]),
    LoginEmail() {
      this.error = ''
      let info = JSON.stringify(
        {
          id : 1,
          name : this.form.username,
          password : this.form.password,
          email : ''
        }
      )
      axios.post('http://localhost:8080/war/api/account/login', info, {
        headers: {
          'Content-Type': 'application/json; charset=utf-8',
          'Accept': 'application/json'
        }
      })
        .then((data) => {
          let response = data.data
          if (response === true) {
            console.log(data.data)
            this.$modal.show('two-factor-modal' , { username : this.form.username, password : this.form.password }) // loginSubmit
          } else {
            this.error = 'username & password combination is not right'
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
    registerViewPush(){
      router.push('/register');
    },
  }
}
</script>

<style scoped>
.btn{
  margin: 20px 10px 0 0;
}
#login{
   color: white;
}
#error{
   color: red;
   font-weight: bold;
}
</style>
