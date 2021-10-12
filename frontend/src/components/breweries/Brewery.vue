<template>
  <div id="breweries">
      
    <div class="brewery-list">
        
        <div class="container-brewery container-blur">
            <router-link class="link-in-black" v-bind:to="{name: 'breweries-display'}">Return to Brewery List</router-link>
        </div>
        <div class="container-brewery container-blur">
            <h2>{{ brewery.name }}</h2>
            <div class="brewery-details">
                <div class="brewery-history a">History: {{ brewery.history }}</div>
                <div class="contact-info a">Contact Information: {{ brewery.contactInfo }}</div>
                <div id="hours-text" class="operation-time a">Hours: 
                    <ul class="hours-list">
                    <li v-for="day in splitJoin(brewery.operationTime)" v-bind:key="day"> {{ day }} </li>
                    </ul>
                    </div>
                <div class="full-address a">
                    <div class="address-line-1">Address: {{ brewery.address }}</div>
                    <div class="address-line-2"> 
                </div>
                    {{brewery.city}},  {{brewery.state}}  {{brewery.zipCode}}  </div>
                
                <div><a :href="brewery.website" target="_blank" class="link-in-black"><img class="logo-image a" :src="brewery.image" alt="Brewery Logo" :href="brewery.website"></a></div>
            <router-link id="reviews-button" class="link-in-black" v-bind:to="{ name: 'brewery-review-display', params: {breweryId: brewery.id } }">View reviews</router-link> | 
            <router-link id="reviews-button" class="link-in-black" v-bind:to="{ name: 'brewery-events', params: {breweryId: brewery.id } }">Upcoming Events</router-link>
            </div>
        </div>
        </div>
        <br><br><br>
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
            breweryService.getById(this.$route.params.breweryId)
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
.container-brewery {
    font-family: 'Poppins', sans-serif;
    font-size: 3vw;
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
.brewery-details {
    font-size: 3vw;
}

.brewery-history {
    font-style:italic;
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
    margin-left: 35%;
}

#return-link {
    font-size: 1.5vw;
}



</style>