<template>
<div>
    <div class="container-events container-blur">
        <router-link id="home" class="link-in-black" v-bind:to="{ name: 'home' }">Back Home</router-link>
        <h2>Upcoming Events:</h2>
    </div>
    <div class="container-events container-blur" v-for="event in allEvents" :key="event.id">
        <h2>Hosted by: {{event.breweryName}}</h2>
        <h2>Event: {{event.name}}</h2>
        <div class="event-details">
        <p>Description: {{event.description}}</p>
        <p>Date: {{convertDate(event.date)}}</p>
        <p>Time: {{convertTime(event.startTime)}} - {{convertTime(event.endTime)}}</p>
        <p>Location: </p>
        <p v-for='(l,i) in convertLocation(event.location)' :key="i">{{l}}</p>
        </div>
    </div>
  </div>
</template>

<script>
import eventService from '../../services/EventService'
import { DateTime }  from "luxon";
export default {
    name: "events",
    data(){
        return{
            allEvents: [],
        }
    },
    created(){
        eventService.getUpcomingEvents().then((response) => {
            this.allEvents = response.data;
        })
    },
    methods: {
        convertTime(time){
            return DateTime.fromFormat(time, 'HH:mm:ss').toFormat('h:mm a');
        },
        convertDate(date){
            return DateTime.fromFormat(date, 'yyyy-mm-dd').toFormat('mm/dd/yyyy');
        },
        convertLocation(location){
            return location.split(':');
        }
    }
};
</script>

<style>
.container-events {
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

.event-details {
    font-size: 2vw;
}

</style>