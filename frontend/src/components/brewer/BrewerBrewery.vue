<template>
  <div id="breweries">
    <div class="brewery-list">
        <div class="container-breweries-brewer container-blur">
            <router-link id="breweries-button" class="link-in-black" v-bind:to="{ name: 'user-breweries-display' }"><br>Return to brewery list </router-link>
            <h2 class="brewery-name">{{ brewery.name }}</h2>
            <div class="brewery-details">
                <h2 v-if="!brewery.active">This brewery is still pending admin approval</h2>
                <div id="edit" v-if="brewery.owner || $store.state.user.authorities[0].name=='ROLE_ADMIN'">
                    Information outdated?
                    <br>
                    <router-link id="edit-brewery-button" class="link-in-black" v-bind:to="{ name: 'edit-brewery-display', params: {breweryId: brewery.id } }">Update here</router-link>
                </div>
                <div class="brewery-history a">History: {{ brewery.history }}</div>
                <div class="contact-info a">Phone Number: <br>{{ brewery.contactInfo }}</div>
                <div id="hours-text" class="operation-time a">Hours: 
                    <ul class="hours-list">
                    <li v-for="day in splitJoin(brewery.operationTime)" v-bind:key="day"> {{ day }} </li>
                    </ul>
                    </div>
                <div class="full-address a">
                <div class="address-line-1">Address: <br><br>{{ brewery.address }}</div>
                <div class="address-line-2"> 
                    </div>
                    {{brewery.city}},  {{brewery.state}}  {{brewery.zipCode}}  </div><br>
                    <div> Type of food served: {{brewery.food}} </div>
            <div><a :href="brewery.website" target="_blank" class="link-in-black"><img class="logo-image a" :src="brewery.image" alt="Brewery Logo" :href="brewery.website"></a></div>
            <router-link id="reviews-button" class="link-in-black" v-bind:to="{ name: 'brewery-review-display', params: {breweryId: brewery.id } }">View reviews</router-link>
            </div>
        </div>
        <br><br><br>
    </div>
  </div>
</template>

<script>
import breweryService from "../../services/BreweryService.js"
export default {
    name: "breweries",
    data() {
        return {
            brewery: {},
            website: ''
        }
        },
        created(){
            breweryService.getByIdLogged(this.$route.params.breweryId)
            .then(response => {
                this.brewery = response.data;
            });
        },
        methods: {
            splitJoin(hours){
                return hours.split(', ');
            }
        }
    }

    

</script>


<style>
.container-breweries-brewer {
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

.brewery-details {
    font-size: 2vw;
}

.brewery-history {
    font-style: italic;
    text-align: left;
}

.a {
    padding: 1.5vw;
}

.logo-image {
    width: 35vw;
}
li {
    text-align: left;
    margin-left: 20%;
}


</style>