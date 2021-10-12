<template>
    <div class="container-featured-beer container-blur">
        <router-link id="home" class="link-in-black" v-bind:to="{ name: 'home' }">Back Home</router-link>
            <h2>Featured Beer:</h2>        
                <div id='grid-featured-beer'>
                    <div><img id='beer-image' :src="featuredBeer.image_url" ></div>                    
                    <div id='beer-details'>
                        <h4>Brewery: {{ featuredBeer.brands }}</h4>
                        <h4>Beer: {{ featuredBeer.product_name }}</h4>
                        <div v-if="featuredBeer.nutriments.alcohol === 0">Sorry, no ABV data found!</div> 
                        <div v-else>
                        <h4>ABV: {{ featuredBeer.nutriments.alcohol }} {{ featuredBeer.nutriments.alcohol_unit }} </h4>
                        </div>
                    </div>
        </div>
    </div>
</template>

<script>
import featuredBeerService from '../../services/FeaturedBeerService'
export default {
    name: 'featured-beer',
    data(){
        return {
            featuredBeer: {}
        }
    },
    created(){
        featuredBeerService.getFeaturedBeer().then((response) => {
            this.featuredBeer = response.data;
        })
    }
}
</script>

<style>

.container-featured-beer {
    font-family: 'Poppins', sans-serif;
    font-size: 3vw;
    text-align: center;
    display: block;
    border-radius:3vw;
    padding: 2vw;
    justify-content: space-evenly;
    justify-items: center;
    align-content: space-evenly;
    align-items: center;
    margin-left: 30vw;
    margin-right: 30vw;
}

#beer-details {
    grid-area: 'details';
    font-size: 2vw;
    text-align: left;
}

#beer-image {
    width: 20vw;
    grid-area: 'image';
    padding: .5vw;
}


#grid-featured-beer {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas:
                         "image details";
    row-gap: 20px;
    column-gap: .5fr;
    align-items: center;
}

</style>