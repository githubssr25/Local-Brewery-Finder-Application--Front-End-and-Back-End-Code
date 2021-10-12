import axios from 'axios'

export default {
    getUpcomingEvents(){
        return axios.get('/events');
    },
    getUpcomingBreweryEvents(id){
        return axios.get(`/breweries/${id}/events`);
    },
    createEvent(id, event){
        return axios.post(`/account/breweries/${id}/events/add`, event);
    },
    getEvent(id){
        return axios.get(`/account/breweries/:breweryId/events/${id}`)
    },
    updateEvent(event){
        return axios.put(`/account/breweries/:breweryId/events/edit`, event);
    },
    deleteEvent(id){
        return axios.delete(`/account/breweries/:breweryId/events`, {params: {id: id}});
    }
}