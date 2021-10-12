<template>
<div class="container-blur container-account">
    <h2 id="username">Welcome {{this.$store.state.user.username}}!</h2>
    <div class="container-accout-options">
    <router-link id="reviews-button" class="link-in-black" v-bind:to="{ name: 'user-reviews-display' }">
      View the reviews you have written </router-link></div>
    <div class="container-accout-options">  
    <router-link id="admin-button" class="link-in-black" v-bind:to="{ name: 'admin-display' }" 
  v-if="$store.state.user.authorities[0].name == 'ROLE_ADMIN'"><br>Admin functions </router-link></div>
  <div class="container-accout-options">
    <router-link id="breweries-button" class="link-in-black" v-bind:to="{ name: 'user-breweries-display' }" 
  v-if="$store.state.user.authorities[0].name == 'ROLE_BREWER'"><br>Manage your breweries </router-link></div>
      <div class="container-accout-options">
  <router-link id="request-brewery-button" class="link-in-black" v-bind:to="{ name: 'request-brewery-display' }" 
  v-if="$store.state.user.authorities[0].name == 'ROLE_BREWER'"><br>Request to add a new brewery </router-link></div>
</div>
</template>

<script>
export default {
    name: 'my-account'

}
</script>

<style>

.container-account {
    font-family: 'Poppins', sans-serif;
    font-size: 3vw;
    text-align: center;
    display: block;
    border-radius: 3vw;
    padding: 1.5vw;
    padding-bottom: 4vw;
    justify-content: space-evenly;
    justify-items: center;
    align-content: space-evenly;
    align-items: center;
    margin-left: 20vw;
    margin-right: 20vw;
}

.container-account-options {
  padding: 3vw;
}

</style>