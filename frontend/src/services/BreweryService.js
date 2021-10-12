import axios from 'axios';



export default {
    getBreweries(){
        return axios.get('/breweries');
    },
    getById(id){
        return axios.get(`/breweries/${id}`);
    },
    getByIdLogged(id){
        return axios.get(`/account/breweries/${id}`);
    },
    getByUserId(){
        return axios.get('/account/breweries');
    },
    isUserOwner(id){
        return axios.get(`/breweries/${id}/user`);
    },
    getFoodList(id){
        return axios.get(`/breweries/${id}/edit`);
    },
    updateBrewery(brewery){
        return axios.put(`/account/breweries/${brewery.id}/edit`, brewery);
    },
    createBrewery(brewery){
        return axios.post(`/account/admin/add`, brewery);
    },
    getBrewers(){
        return axios.get(`/account/admin/add`);
    },
    updateBrewer(brewery){
        return axios.put(`/account/admin/assign`, brewery);
    },
    deleteBrewery(id){
        return axios.delete(`/account/breweries`, { params: {id: id} })
    }
}