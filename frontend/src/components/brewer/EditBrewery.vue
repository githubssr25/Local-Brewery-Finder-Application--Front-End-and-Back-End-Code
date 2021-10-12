<template>
  <div id='edit-brewery' class="container-edit-brewery container-blur">
      <router-link id="brewery-button" class="link-in-black" v-bind:to="{ name: 'brewer-brewery-display', params: {breweryId: editedBrewery.id } }">Return to brewery</router-link>
      <form v-on:submit.prevent="updateBrewery">
          <div id="update-brewery">Update Brewery Info: </div><br>
          <div class="form-element">
              <label for="name">Name: </label><br>
              <input id="name" type="text" v-model="editedBrewery.name" />
          </div>
          <br>
          <div class="form-element">
              <label for="history">History: </label><br>
              <textarea id="history" type="text" v-model="editedBrewery.history" />
          </div>
          <br>
          <div class="form-element">
              <label for="contact-info">Phone Number: </label><br>
              <input id="contact-info" type="text" v-model="editedBrewery.contactInfo" /> 
          </div>
          <br>
          <div class="form-element">
              <label for="operation-time">Operation Time: </label>
              <div v-for="(day, i) in days" v-bind:key="i">
                  <label for="day-hours">{{day}}</label>
                  <input id="day-hours" type="text" v-model="hours[i]"/>
              </div>
          </div>
          <br>
          <div class="form-element">
              <label for="address">Street Address: </label>
              <input id="address" type="text" v-model="editedBrewery.address" />
          </div>
          <div class="form-element">
              <label for="city">City: </label>
              <input id="city" type="text" v-model="editedBrewery.city" />
          </div>
          <div class="form-element">
              <label for="state">State: </label>
              <input id="state" type="text" v-model="editedBrewery.state" />
          </div>
          <div class="form-element">
              <label for="zip">Zip Code: </label>
              <input id="zip" type="text" v-model="editedBrewery.zipCode" />
          </div>
          <br>
          <div class="form-element">
              <label for="website">Link to Website: </label><br>
              <input id="website" type="text" v-model="editedBrewery.website" />
          </div>
          <br>
          <div class="form-element">
              <label for="image">Link to Image: </label><br>
              <input id="image" type="text" v-model="editedBrewery.image" />
          </div>
          <br>
          <div class="form-element">
              <label for="food">Type of food offered: </label>
              <select id="food" type="text" v-model="editedBrewery.foodId">
                  <option v-bind:value="food.id" v-for="food in foodList" v-bind:key="food.id">
                      {{ food.name }}</option>
              </select>
          </div>
          <br>
          <input class="form-btns" type="submit" value="Save">
          <input class="form-btns" type="button" value="Cancel" v-on:click.prevent="resetForm()">
          </form>
  </div>
</template>

<script>
import breweryService from '../../services/BreweryService'
export default {
    name: 'edit-brewery',
    data(){
        return {
            editedBrewery: {},
            foodList: [],
            days: ['Mon: ', 'Tue: ', 'Wed: ', 'Thr: ', 'Fri: ', 'Sat: ', 'Sun: '],
            hours: []
        }
    },
    created() {
        breweryService.getById(this.$route.params.breweryId).then((response) => {
            this.editedBrewery = response.data;
            this.setHours()
        }),
        breweryService.getFoodList(this.$route.params.breweryId).then((response) => {
            this.foodList = response.data;
        })
    },
    methods: {
         setOperationTime(){
            this.editedBrewery.operationTime = this.days.map((e, i) => e + this.hours[i]).join(', ');
        },
        setHours(){
            this.hours = this.editedBrewery.operationTime.split(', ');
            for(let i = 0; i<7; i++){
                this.hours[i] = this.hours[i].split(' ')[1];
            }
        },
        updateBrewery(){
            this.setOperationTime()
            breweryService.updateBrewery(this.editedBrewery).then((response) => {
            if (response.status == 200 ){
                this.$router.push(`/account/breweries/${this.editedBrewery.id}`);
            }
            })
            .catch(error => {
                this.handleErrorResponse(error, "updating");
            })
        },
        resetForm() {
            breweryService.getById(this.$route.params.breweryId).then((response) => {
            this.editedBrewery = response.data;
            this.setHours()
        })
        }
    }

}
</script>

<style>

#update-brewery{
    font-family: 'Poppins', sans-serif;
    font-size: 3vw;
}

.container-edit-brewery {
    font-family: 'Poppins', sans-serif;
    font-size: 2vw;
    text-align: center;
    display: block;
    border-radius: 3vw;
    padding: 5vw;
    justify-content: space-evenly;
    justify-items: center;
    align-content: space-evenly;
    align-items: center;
    margin-left: 20vw;
    margin-right: 20vw;
}

</style>