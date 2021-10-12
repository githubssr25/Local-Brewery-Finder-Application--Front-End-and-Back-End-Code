import axios from 'axios';


export default {
    getBeers(id){
        return axios.get(`/breweries/${id}/beers`);
    },
    getBeersById(breweryId, id){
        return axios.get(`/breweries/${breweryId}/beers/${id}`);
    },
    deleteBeer(id){
        return axios.delete(`/account/breweries/:breweryId/beers`, {params: {beerId: id} })
    },
    createBeer(breweryId, beer){
        return axios.post(`/account/breweries/${breweryId}/beers/create`, beer)
    },
    updateBeer(breweryId, beer){
        return axios.put(`/account/breweries/${breweryId}/beers/${beer.id}/edit`, beer);
    }
}