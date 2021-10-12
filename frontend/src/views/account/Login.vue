<template>
  <div id="login" class="text-center container-login container-blur">
    <form class="form-signin" @submit.prevent="login">
      <h2 class="h3 mb-3">Sign In</h2>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only login-text">Username</label>
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
      <label for="password" class="sr-only login-text">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <br>
      <button class="form-btns" type="submit">Sign in</button>
      <br>
      <router-link :to="{ name: 'register' }" class="login-text link-in-black">Need an account?</router-link>  
    </form>
  </div>
</template>

<script>
import authService from "../../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap');

h2 {
  font-size: 3vw;
}

.container-login {
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

.login-text {
    padding: 2vw;
    font-family: 'Poppins', sans-serif;
    font-size: 2vw;
    text-align: center;
}

</style>
