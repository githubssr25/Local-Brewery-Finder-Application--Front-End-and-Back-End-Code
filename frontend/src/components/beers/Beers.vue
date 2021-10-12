<template>
    <div>
        <div class="container-beer-list container-blur">
            <router-link class="link-in-black" id=“breweries-list” v-bind:to="{name: 'breweries-display'}">Return to Brewery List</router-link>
        </div>
        <div class="container-beer-list container-blur">
            <h1 class="beer-list-header">{{breweryDetails.name}} Presents: </h1>
            <div v-for="beer in allBeers" v-bind:key="beer.id">
                <router-link class="link-in-black beer-name-in-list" v-bind:to="{name: 'beer-display', params: {breweryId: breweryDetails.id, beerId: beer.id} }">{{beer.name}}</router-link>
                <br><br>
            </div>
        </div>
    </div>
</template>

<script>
import beerService from '../../services/BeerService'
import breweryService from '../../services/BreweryService'
export default {
    name: 'beers-list',
    data(){
        return {
            allBeers: [],
            breweryDetails: {}
        }
    },
    created(){
        beerService.getBeers(this.$route.params.breweryId).then((response) => {
            this.allBeers = response.data;
        })
        breweryService.getById(this.$route.params.breweryId).then((response) => {
            this.breweryDetails = response.data;
        })
    }

}
</script>

<style>

.beer-list-header {
    font-size: 3vw;
    font-style: bold;
}

.beer-name-in-list {
    font-size: 2.5vw;
    font-style: italic;
}

.container-beer-list {
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