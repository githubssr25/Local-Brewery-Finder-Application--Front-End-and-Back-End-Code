<template>
<div id="brewery-form">
    <form class="container-add-brewery container-blur" v-on:submit.prevent="addNewBrewery">
        <router-link class="link-in-black" v-bind:to="{ name: 'my-account-display' }">Return to account menu</router-link>
        <h2 id="add-brew">Add a brewery: </h2>
        <br>
        <div class="form-element">
            <label for="name">Name: </label><br>
            <input id="name" type="text" v-model="newBrewery.name" />
        </div>
        <br>
        <div class="form-element">
            <label for="history">History: </label><br>
            <textarea id="history" type="text" v-model="newBrewery.history" />
        </div>
        <br>
        <div class="form-element">
            <label for="contact-info">Phone Number: </label><br>
            <input id="contact-info" type="text" v-model="newBrewery.contactInfo" />
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
            <label for="address">Street Address: </label><br>
            <input id="address" type="text" v-model="newBrewery.address" />
        </div>
        <div class="form-element">
            <label for="city">City: </label><br>
            <input id="city" type="text" v-model="newBrewery.city" />
        </div>
        <div class="form-element">
            <label for="state">State: </label><br>
            <input id="state" type="text" v-model="newBrewery.state" />
        </div>
        <div class="form-element">
            <label for="zip">Zip Code: </label><br>
            <input id="zip" type="text" v-model="newBrewery.zipCode" />
        </div>
        <br>
        <div class="form-element">
            <label for="website">Link to brewery website: </label><br>
            <input placeholder="https://" id="website" type="text" v-model="newBrewery.website" />
        </div>
        <br>
        <div class="form-element">
            <label for="image">Link to brewery image: </label><br>
            <input placeholder="https://" id="image" type="text" v-model="newBrewery.image" />
        </div>
        <br>
        <div class="form-element">
            <label for="food">Type of food offered: </label><br>
            <select id="food" type="text" v-model="newBrewery.foodId">
                <option v-bind:value="food.id" v-for="food in foodList" v-bind:key="food.id">
                    {{ food.name }}</option>
            </select>
        </div>
        <br>
        <div class="submit-save-bttn">
        <input class="form-btns" type="submit" value="Save">
        <input class="form-btns" type="button" value="Cancel" v-on:click.prevent="resetForm()">
        </div>
    </form>
</div>
</template>

<script>
import breweryService from '../../services/BreweryService'
export default {
    name: 'request-brewery',
    data(){
        return {
            newBrewery: {
                active: false,
                ownerId: this.$store.state.user.id
            },
            foodList: [],
            days: ['Mon: ',  'Tue:  ', 'Wed: ', 'Thr: ', 'Fri: ', 'Sat: ', 'Sun: '],
            hours: [] 
        }
    },
    created() {
        breweryService.getFoodList(this.$route.params.breweryId).then((response) => {
            this.foodList = response.data;
        })
    },
    methods: {
        setOperationTime(){
            this.newBrewery.operationTime = this.days.map((e, i) => e + this.hours[i]).join(', ');
        },
        addNewBrewery() {
            this.setOperationTime()
            breweryService.createBrewery(this.newBrewery).then((response) => {
            if (response.status == 201 ){
                this.$router.push(`/`);
            }
            })
            .catch(error => {
                alert('Error requesting this brewery to be added.');
                console.log(error);
            })
        },
        resetForm() {
            this.newBrewery = {
                active: false
            }
        }
    }


}
</script>

<style>

.container-add-brewery {
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

</style>