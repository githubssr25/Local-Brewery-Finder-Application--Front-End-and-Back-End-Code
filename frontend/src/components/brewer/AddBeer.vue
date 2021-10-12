<template>
<div id="beer-form">
  <form class="container-add-beer container-blur" v-on:submit.prevent="addNewBeer()">
      <router-link id="brewery-beers" class="link-in-black" v-bind:to="{name: 'user-beers-display', params: {breweryId: this.$route.params.breweryId } }">Return to beer list</router-link> <br>
      <h2 id="add-beer">Add beer: </h2><br>
          <div class="form-element">
              <label for="name">Beer name: </label><br>
              <input id="name" type="text" v-model="newBeer.name" />
          </div>
          <br>
          <div class="form-element">
              <label for="description">Beer description: </label><br>
              <textarea id="description" type="text" v-model="newBeer.description" />
          </div>
          <br>
          <div class="form-element">
              <label for="beer-type">Beer type: </label><br>
              <input id="beer-type" type="text" v-model="newBeer.beerType" />
          </div>
          <br>
          <div class="form-element">
              <label for="abv">Beer abv: </label><br>
              <input id="abv" type="text" v-model="newBeer.abv" />
              <label for="abv">%</label><br>
          </div>
          <br>
          <div class="form-element">
              <label for="gluten-free">Is your beer Gluten Free? </label>
              <input type="checkbox" id="gluten" v-model="newBeer.glutenFree" />
          </div>
          <br>
          <div class="form-element">
              <label for="image">Please enter a link to an image for the beer: </label>
              <input placeholder="https://" id="image" type="text" v-model="newBeer.imageLink" />
          </div>
          <br>
          <input class="form-btns" type="submit" value="Save">
          <input class="form-btns" type="button" value="Cancel" v-on:click.prevent="resetForm()">
          </form>
</div>
</template>

<script>
import beerService from '../../services/BeerService'
export default {
    name: 'add-beer',
    data(){
        return {
            newBeer: {
                glutenFree: false,
                breweryId: this.$route.params.breweryId
            }
        }
    },
    methods: {
        addNewBeer() {
            beerService.createBeer(this.$route.params.breweryId, this.newBeer).then((response) => {
            if (response.status == 201 ){
                this.$router.push(`/account/breweries/${this.$route.params.breweryId}/beers`);
            }
            })
            .catch(error => {
                alert("Could not add this Beer.");
                console.error(error);
            })
        },
        resetForm() {
            this.newBeer = {glutenFree: false, breweryId: this.$route.params.breweryId}
        }
    }


}
</script>

<style>

.container-add-beer {
  font-family: 'Poppins', sans-serif;
  font-size: 2vw;
  text-align: left;
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

</style>