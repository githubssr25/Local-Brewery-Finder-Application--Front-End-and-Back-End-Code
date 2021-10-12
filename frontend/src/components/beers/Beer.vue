<template>
<div>
  <div class="container-beer-details container-blur">
    <router-link class="link-in-black" v-bind:to="{ name: 'beers-display', params: {breweryId: breweryDetails.id } }">Return to Beer List</router-link>
  </div>
  <div class="container-beer-details container-blur">
    <div><img class="logo-image" :src="beerDetails.imageLink" ></div>
    <h2 class="beer-name-text">{{beerDetails.name}}</h2>
    <h3 class="beer-type-text">Beer Type: {{beerDetails.beerType}}</h3>
    <p class="beer-details-description">{{beerDetails.description}}</p>
    <p class="beer-abv-text">Alcohol By Volume: {{beerDetails.abv}}%</p>
    <router-link id="reviews-button" class="link-in-black" v-bind:to="{ name: 'beer-review-display', params: {breweryId: breweryDetails.id, beerId: beerDetails.id} }">View reviews</router-link>
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
            beerDetails: {},
            breweryDetails: {}
        }
    },
    created(){
        beerService.getBeersById(this.$route.params.breweryId, this.$route.params.beerId).then((response) => {
            this.beerDetails = response.data;
        })
        breweryService.getById(this.$route.params.breweryId).then((response) => {
            this.breweryDetails = response.data;
        })
    }

}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap');



.beer-name-text {
    font-size: 3vw;
}

.beer-type-text{
    font-size: 3vw;
}

.beer-details-description {
    font-size: 2.5vw;
    font-style: italic;
    text-align: left;
}

.beer-abv-text{
    font-size: 2.5vw;

}

.container-beer-details {
    font-family: 'Poppins', sans-serif;
    font-size: 3vw;
    text-align: center;
    display: block;
    border-radius: 3vw;
    padding: 2vw;
    padding-bottom: 4vw;
    justify-content: space-evenly;
    justify-items: center;
    align-content: space-evenly;
    align-items: center;
    margin-left: 20vw;
    margin-right: 20vw;
}
</style>