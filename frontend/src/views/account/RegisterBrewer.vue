<template>
  <div id="register" class="text-center container-register-brewer container-blur">
    <form class="form-register" @submit.prevent="register">
      <h2 class="h3 mb-3 font-weight-normal">Create Brewer Account</h2>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only brewer-register-text">Brewer Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <br>
      <label for="password" class="sr-only brewer-register-text">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <br>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <br>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Brewery Account
      </button>      
      <br>
      <router-link :to="{ name: 'login' }" class="brewer-register-text link-in-black">Have an account?</router-link>
    </form>
  </div>

</template>

<script>
import authService from '../../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: "brewer",
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>

@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap');

.container-register-brewer {
  font-family: 'Poppins', sans-serif;
  font-size: 3vw;
  text-align: center;
  display: block;
  border-radius:3vw;
  padding: 3vw;
  justify-content: space-evenly;
  justify-items: center;
  align-content: space-evenly;
  align-items: center;
  margin-left: 20vw;
  margin-right: 20vw;
}

.brewer-register-text {
  padding: 2vw;
  font-family: 'Poppins', sans-serif;
  font-size: 2vw;
  text-align: center;
}

</style>
