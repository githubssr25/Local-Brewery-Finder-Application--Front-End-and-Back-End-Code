<template>
<div class="container-events container-blur">
    <router-link id="brewery-events-button" class="link-in-black" v-bind:to="{ name: 'manage-brewery-events', params: {breweryId: this.$route.params.breweryId } }">Back to events</router-link><br>
  <form id="edit-review-form" v-on:submit.prevent="updateEvent">
    <div class="form-element">
        <label for="title">Name for the event:</label>
        <textarea class="title" placeholder="Event Title" v-model="editedEvent.name"/>
    </div>
    <div class="form-element">
        <label for="text">Description for the Event:</label>
        <textarea class="text" placeholder="Event Description" v-model="editedEvent.description"/>
    </div>
    <div class="form-element">
        <label for="name">Date of the Event:</label>
        <input type="date" class="name" placeholder="Name" v-model="editedEvent.date"/>
    </div>
    <div class="form-element">
        <label for="name">Event start time:</label>
        <input type="time" class="name" placeholder="Name" v-model="editedEvent.startTime"/>
    </div>
    <div class="form-element">
        <label for="name">Event end time:</label>
        <input type="time" class="name" placeholder="Name" v-model="editedEvent.endTime"/>
    </div>
    <div class="buttons-div">
        <input type="submit" value="Update Event" class="form-btns">
        <input type="button" value="Cancel" class='form-btns' v-on:click.prevent="resetForm">
    </div>
  </form>
</div>
</template>

<script>
import eventService from '../../services/EventService'
export default {
    name: 'edit-event',
    data(){
        return{
            editedEvent: {}
        }
    },
    created(){
        eventService.getEvent(this.$route.params.eventId).then((response) => {
            this.editedEvent = response.data;
        })
    },
    methods: {
        updateEvent(){
            eventService.updateEvent(this.editedEvent).then((response) => {
                if (response.status === 200){
                    this.$router.push(`/account/breweries/${this.$route.params.breweryId}/events`)
                }
            })
        },
        resetForm(){
            eventService.getEvent(this.$route.params.eventId).then((response) => {
                this.editedEvent = response.data;
            })
        }
    }

}
</script>

<style>

</style>