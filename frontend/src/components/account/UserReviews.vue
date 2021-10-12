<template>
    <div>
        <div class="container-blur nav-container">
            <router-link class="link-in-black" v-bind:to="{ name: 'my-account-display' }" v-if="$store.state.token != ''">My Account</router-link>
            <h2 v-if="!allReviews.length">You currently have no reviews written. Get out there and rate!</h2>
            </div>
        <div class="container-blur beer-reviews-container" v-for="review in allReviews" v-bind:key="review.id">
            <h2> "{{ review.title }}" </h2>      
            <h3> By: {{ review.name }} </h3>
            <div id='rating'>
                <h3>Rating:</h3>
                <img class="rating-beer-pint" src="../../images/beer-mugs.png" v-for="n in review.starCount" v-bind:key="n"/>
                <img class="rating-beer-pint" src="../../images/empty-mugs.png" v-for="n in emptyMugCount(review.starCount)" v-bind:key="n"/>
            </div>
            <p> "{{ review.text }}" </p>
        </div>
    </div>
</template>

<script>
import reviewService from '../../services/ReviewService'
export default {
    name: 'user-reviews',
    data () {
        return {
            allReviews: []
        }
    },
    created() {
        reviewService.getUserReviews(this.$store.state.user.id).then((response) => {
            this.allReviews = response.data;
        })
    },
    methods: {
        emptyMugCount(count){
            return 5-count;
        }
    }

}
</script>

<style>
.rating-beer-pint{
margin: auto;
height: 7vw;
}

.beer-reviews-container {
    font-family: 'Poppins', sans-serif;
    font-size: 20px;
    text-align: left;
    display: block;
    border-radius: 3vw;
    padding: 3vw;
    justify-content: space-evenly;
    justify-items: left;
    align-content: space-evenly;
    align-items: left;
    margin-left: 20vw;
    margin-right: 20vw;
}

.nav-container {
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


</style>