import axios from 'axios';



export default {
    getBreweryReviews(id){
        return axios.get(`/breweries/${id}/reviews`);
    },
    getBeerReviews(beerId){
        return axios.get(`/breweries/:breweryId/beers/${beerId}/reviews`);
    },

    createReview(review){
        return axios.post('/breweries/${breweryId}/reviews', review);
      },

      createBeerReview(review){
        return axios.post('/breweries/${breweryId}/beers/${beerId}/reviews', review);
      },
      getUserReviews(){
        return axios.get('/account/reviews');
      }
}