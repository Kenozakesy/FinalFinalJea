<template>
   <modal name="two-factor-modal"
          id="modal-test"
          :adaptive="true"
          :scrollable="false"
          :max-width=600
          height="100%"
          @before-open="beforeOpen"
          @before-close="beforeClose">
      <p>{{ loginError }}</p>
      <form id="scroll" class="overflow-y-auto p-8 center w-full h-full" @submit.prevent="insertCode">
         <div class="center w-full flex-col">

            <h2 align="center">E-mail Code</h2>

            <p v-if="this.Error !== ''" class="error" align="center">{{ Error }}</p>

            <div class="w-full">
               <div class="flex flex-col p-4">
                  <input class="form-control"
                         v-model="form.signInCode"
                         type="text"
                         placeholder="logIn code">

                  <button class="btn btn-primary margin" >
                     LogIn
                  </button>
               </div>
            </div>
         </div>
      </form>

   </modal>
</template>

<script>
// import axios from 'axios'
import { mapState, mapActions } from 'vuex'

export default {
  name: 'two-factor-modal',
  data () {
    return {
      Error: '',
      form: {
        username: '',
        password: '',
        signInCode: ''
      }
    }
  },
  computed: {
    ...mapState([
      'loggingIn',
      'loginError'
    ])
  },
  methods: {
    ...mapActions([
      'doLogin'
    ]),
    beforeOpen (e) {
      this.form.username = e.params.username
      this.form.password = e.params.password
    },
    beforeClose () {
      this.Error = ''
      this.form.signInCode = ''
    },
    insertCode () {
      this.Error = ''
      if (!this.form.signInCode) {
        this.Error = 'Not a valid code'
      } else {
        this.doLogin({
          username: this.form.username,
          password: this.form.password,
          signInCode: this.form.signInCode
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
