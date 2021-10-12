<template>
<div>
    <div class="container-events container-blur">
        <router-link id="brewery-details-button" class="link-in-black" v-bind:to="{ name: 'user-breweries-display' }">Back to Breweries</router-link><br>
        <button class="form-btns" v-if="showAddForm === false" v-on:click.prevent="showAddForm = true">Add Event</button>
        <form id="add-review-form" v-if="showAddForm===true" v-on:submit.prevent="addNewEvent">
            <div class="form-element">
                <label for="title">Name for the event: </label>
                <textarea class="title" placeholder="Event Title" v-model="newEvent.name"/>
            </div>
            <div class="form-element">
                <label for="text">Description for the Event: </label>
                <textarea class="text" placeholder="Event Description" v-model="newEvent.description"/>
            </div>
            <div class="form-element">
                <label for="name">Date of the Event: </label>
                <input type="date" class="name" placeholder="Name" v-model="newEvent.date"/>
            </div>
            <div class="form-element">
                <label for="name">Event start time: </label>
                <input type="time" class="name" placeholder="Name" v-model="newEvent.startTime"/>
            </div>
            <div class="form-element">
                <label for="name">Event end time: </label>
                <input type="time" class="name" placeholder="Name" v-model="newEvent.endTime"/>
            </div>
            <br>
            <div class="buttons-div">
                <input type="submit" value="Save Event" class="form-btns">
                <input type="button" value="Cancel" class='form-btns' v-on:click.prevent="resetAddForm">
            </div>
        </form>
        <h2>Upcoming Events for {{brewery.name}}:</h2>
    </div>
    <div class="container-events container-blur" v-for="(event, ind) in allEvents" :key="event.id">
        <h2>{{event.breweryName}}</h2>
        <h2>{{event.name}}</h2>
        <p>{{event.description}}</p>
        <h2>{{convertTime(event.startTime)}} - {{convertTime(event.endTime)}}</h2>
        <h2>{{convertDate(event.date)}}</h2>
        <h2 v-for='(l,i) in convertLocation(event.location)' :key="i">{{l}}</h2>
        <router-link id="edit-event" class="link-in-black" v-bind:to="{ name: 'edit-event', params: {breweryId: brewery.id, eventId: event.id} }">Edit Event </router-link><br>
        <button class="form-btns" v-on:click.prevent="deleteEvent(event.id, ind)">Delete Event</button>
        

    </div>
  </div>
</template>

<script>
import breweryService from '../../services/BreweryService'
import eventService from '../../services/EventService'
import { DateTime }  from "luxon";
export default {
    name: "manage-brewery-events",
    data(){
        return{
            allEvents: [],
            brewery: {},
            showAddForm: false,
            newEvent: {breweryId: this.$route.params.breweryId},
            editedEvent: {}
        }
    },
    created(){
        eventService.getUpcomingBreweryEvents(this.$route.params.breweryId).then((response) => {
            this.allEvents = response.data;
            for (let i = 0; i < this.allEvents.length; i++){
                this.allEvents[i].showEditForm = false;
            }
        }),
        breweryService.getById(this.$route.params.breweryId).then(response => {
                this.brewery = response.data;
        });
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
        },
        resetAddForm(){
            this.newEvent = {
                breweryId: this.$route.params.breweryId
            };
            this.showAddForm = false;
            window.location.reload();
        },
        addNewEvent(){
            eventService.createEvent(this.$route.params.breweryId, this.newEvent).then((response) => {
                if (response.status === 201){
                    this.newEvent.title = "Your new Event: " + this.newEvent.title;
                    this.newEvent.id = response.data;
                    this.allEvents.unshift(this.newEvent);
                    this.resetAddForm();
                }
            }).catch((error) => {
                alert('Error creating this event');
                console.log(error);
            })
        },
        deleteEvent(id, i){
            eventService.deleteEvent(id);
            this.allEvents.splice(i, 1);
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
}

</style>