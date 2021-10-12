<template>
  <div id="app">
    <div id="main-nav">

      <router-link class="main-nav" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;

      <router-link class="main-nav" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> 

      <router-link class="main-nav" v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link> &nbsp;|&nbsp;

      <router-link class="main-nav" v-bind:to="{ name: 'register' }" v-if="$store.state.token == ''">Register</router-link>

      <router-link class="main-nav" v-bind:to="{ name: 'my-account-display' }" v-if="$store.state.token != ''">My Account</router-link>
    

    </div>
        <h1 id="main-header">
          Brewery Finder
        </h1>	
    <router-view />
  </div>
</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Lobster+Two:ital,wght@1,700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Staatliches&display=swap');

#main-header {
  font-family: 'Lobster Two', cursive;
  font-size: 12vw;
  color: white;
  text-align: center;
  text-shadow:
  -.5vw -.5vw 0 #000,
  .5vw -.5vw 0 #000,
  -.5vw .5vw 0 #000,
  .5vw .5vw 0 #000
  ;
}
html {
  background: url("https://images.unsplash.com/photo-1555658636-6e4a36218be7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80")  no-repeat center fixed;
  background-size: cover;
}

#main-nav {
  text-align: center;
  font-size: 5vw;
  color: white;
}

.main-nav {
  font-family: 'Staatliches', cursive;
  font-size: 5vw;
  color: white;
}

.button:visited {
  font-family: 'Staatliches', cursive;
  font-size: xx-large;
  color: white;
}

.center-button {
    display: block;
    margin-left: auto;
    margin-right: auto;
    width: 50%;
    justify-content: space-evenly;
    justify-items: center;
    align-content: space-evenly;
    align-items: center;
    text-align: center;
    padding: 3vw;
}

.container-blur {
  background-color: rgba(255 ,255 , 255, .5);    
  backdrop-filter: blur(12px);
  margin: 1.5vw;
}


.form-btns {
  font-family: 'Poppins', sans-serif;
  font-size: 2vw;
}

</style>