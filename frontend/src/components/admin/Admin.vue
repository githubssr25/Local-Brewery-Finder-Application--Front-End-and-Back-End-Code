<template>
  <div id='admin-menu'>
    <div class="container-admin-function container-blur">
      <router-link class="link-in-black" v-bind:to="{ name: 'my-account-display' }">My Account</router-link>
    </div>
    <div class="container-admin-function container-blur">
      <router-link id="map-button" class="link-in-black" v-bind:to="{ name: 'manage-brewers-display' }">Map Brewer to Brewery</router-link>
    </div>
    <div class="container-admin-function container-blur">
      <router-link id="add-button" class="link-in-black" v-bind:to="{ name: 'add-brewery-display' }">Add New Brewery</router-link>
    </div>
    <div class="container-admin-function container-blur">
      <router-link id="add-button" class="link-in-black" v-bind:to="{ name: 'brewery-requests-display' }">View Brewery Requests</router-link>
    </div>
  </div>
</template>

<script>
export default {
    name: 'admin'

}
</script>

<style>
.container-admin-function {
    font-family: 'Poppins', sans-serif;
    font-size: 3vw;
    text-align: center;
    display: block;
    border-radius: 3vw;
    padding: 1.5vw;
    justify-content: space-evenly;
    justify-items: center;
    align-content: space-evenly;
    align-items: center;
    margin-left: 20vw;
    margin-right: 20vw;
}
</style>