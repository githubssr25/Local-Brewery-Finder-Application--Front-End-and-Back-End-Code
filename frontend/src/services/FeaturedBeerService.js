import axios from 'axios';


export default {
    getFeaturedBeer(){
        return axios.get(`/beers/random`);
    },

}