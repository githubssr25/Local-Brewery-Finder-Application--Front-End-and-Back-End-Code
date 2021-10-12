<template>
<div>
    <div id='beer-list'>
        <div class="container-beers-brewer container-blur">
            <router-link id="breweries-list" class="link-in-black" v-bind:to="{name: 'user-breweries-display'}">Return to Brewery List</router-link>
            <h2 id='brewery-name-at-top'>{{breweryDetails.name}} </h2>
        </div>        
        <div class="container-beers-brewer container-blur">
            <router-link id="add-a-beer" class="link-in-black" v-bind:to="{name: 'add-beer-display'}">Add a beer</router-link>
        </div>
        <div id='beers' v-for="beer in allBeers" class="container-beers-brewer container-blur" v-bind:key="beer.id">
            <h2 id='beer-name'>{{beer.name}}</h2>
            <router-link id='edit-beer' class="link-in-black" v-bind:to="{name: 'edit-beers-display', params: {beerId: beer.id}}">Edit this beer</router-link>
            <br>
            <button id="delete-beer" class="form-btns" v-on:click.prevent="deleteBeer(beer.id)">Delete this beer</button>
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
            breweryDetails: {},
        }
    },
    created(){
        beerService.getBeers(this.$route.params.breweryId).then((response) => {
            this.allBeers = response.data;
        })
        breweryService.getById(this.$route.params.breweryId).then((response) => {
            this.breweryDetails = response.data;
        })
    },
    methods: {
        deleteBeer(id){
            beerService.deleteBeer(id).then((response) => {
                if(response.status===200) {
                    window.location.reload();
                }
            })
            .catch(error=> {
                    alert("Could not delete this Beer.");
                    console.error(error);
                })
        },
        pushToEdit(id){
            this.$router.push(`/account/breweries/${this.$route.params.breweryId}/beers/${id}/edit`)
        }
      
    }

}
</script>

<style>

.container-beers-brewer {
    font-family: 'Poppins', sans-serif;
    font-size: 3vw;
    text-align: center;
    display: block;
    border-radius:3vw;
    padding: 5vw;
    justify-content: space-evenly;
    justify-items: center;
    align-content: space-evenly;
    align-items: center;
    margin-left: 20vw;
    margin-right: 20vw;
}



</style>