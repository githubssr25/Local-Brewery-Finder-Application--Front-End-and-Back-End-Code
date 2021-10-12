<template>
  <div>
    <div class="brewery-list" v-for="brewery in allBreweries" v-bind:key="brewery.id">
        <div class="container-breweries container-blur" v-if="brewery.active">
            <h2 class="brewery-name-header">{{ brewery.name }}</h2>
            <router-link id="brewery-details-button" class="link-in-black" v-bind:to="{ name: 'brewery-display', params: {breweryId: brewery.id } }">View details</router-link>
            |
            <router-link id="beers-button" class="link-in-black" v-bind:to="{ name: 'beers-display', params: {breweryId: brewery.id } }">View beers</router-link>
        </div>
        <br>
    </div>
  </div>
</template>

<script>
import breweryService from "../../services/BreweryService.js"
export default {
    name: "breweries",
    data() {
        return {
            allBreweries: [],
            website: ''
        }
        },
        created(){
            breweryService.getBreweries()
            .then(response => {
                this.allBreweries = response.data;
            }); 
        }
    }

    

</script>


<style>
.container-breweries {
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


</style>