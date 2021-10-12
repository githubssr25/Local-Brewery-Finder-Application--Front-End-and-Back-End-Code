<template>
  <div>
      <div class="center-nav review-container container-blur">
        <router-link id="brewery-details-button" class="link-in-black" v-bind:to="{ name: 'beer-display', params: {breweryId: this.$route.params.breweryId, beerId: this.$route.params.beerId } }">Back to Beer</router-link>
    </div>
      <div class="review-container container-blur" v-if="$store.state.token == '' && allReviews.length==0">
            <h2>There are no reviews on this beer yet. Log in to an account to add one!</h2>
    </div>
    <div class="review-container container-blur" v-if="$store.state.token != ''">
            <div id="add-review" >
                <div class="center-button">
                    <button class="form-btns" v-if="showForm === false" v-on:click.prevent="showForm = true">Click here to add a review!</button>
                </div>
                <h2 id="no-reviews" v-if="!allReviews.length && showForm==false">There are no reviews on this beer yet. Be the first to write one!</h2>
            </div>
            <form class="add-review-form" v-if="showForm===true" v-on:submit.prevent="addReview">
                <h2>Add beer review: </h2>
                <div class="form-element">
                    <label for="title">Title of your review: </label>
                    <br>
                    <textarea v-model="newReview.title"/>
                    <br>
                </div>
                <br>
                <div class="form-element">
                    <label for="text">Detailed thoughts on this beer: </label>
                    <br>
                    <textarea v-model="newReview.text"/>
                    <br>
                </div>
                <br>
                <div class="form-element">
                    <label for="name">Enter your name: </label>
                    <br>
                    <input type="text" v-model="newReview.name"/>
                    <br>
                </div>
                <br>
                <div class="form-element">
                    <label for="star-rating">Select your rating:    </label>
                    <select name="star-rating" v-model="newReview.starCount">
                        <option value=1>1</option>
                        <option value=2>2</option>
                        <option value=3>3</option>
                        <option value=4>4</option>
                        <option value=5>5</option>
                        <br>
                    </select>
                </div>
                <br>
                <div class="buttons-div">
                    <input type="submit" value="Save Review" class="form-btns">
                    <input type="button" value="Cancel" class='form-btns' v-on:click.prevent="resetForm">
                </div>
            </form>
        </div>
    <div class="container-blur review-container" v-for="review in allReviews" v-bind:key="review.id">
        <!-- This is where all the reviews will display-->
        <h2> "{{ review.title }}" </h2>      
        <h3> By: {{ review.name }} </h3>
        <div id='rating'>
            <h3>Rating:</h3>
            <img class="rating-beer-pint" src="../../images/beer-mugs.png" v-for="n in review.starCount" v-bind:key="n"/>
            <img class="rating-beer-pint" src="../../images/empty-mugs.png" v-for="n in emptyMugCount(review.starCount)" v-bind:key="n"/>
        </div>  
        <p> "{{ review.text }}" </p>
    </div>
    <br><br>
  </div>
</template>
<script>
import reviewService from '../../services/ReviewService'
export default {
    name: 'beer-reviews',
    data(){
        return {
            showForm: false,
            allReviews: [],
            newReview: {
                type: 'Beer',
                targetId: this.$route.params.beerId
            }
        }
    },

    created() {
        reviewService.getBeerReviews(this.$route.params.beerId).then((response) => {
            this.allReviews = response.data;
        })
    },
    methods: {
        resetForm() {
            this.newReview = {
                        type: 'Beer',
                        targetId: this.$route.params.beerId
            };
            this.showForm = false;
        },

        addReview() {
            reviewService.createBeerReview(this.newReview)
            .then(response=> {
                if (response.status === 201) {
                    this.resetForm();
                    window.location.reload();
                }
            })
        },
        emptyMugCount(count){
            return 5-count;
        }
    }
}
</script>

<style>

#no-reviews{
    font-family: 'Poppins', sans-serif;
    font-size: 2vw;
}

.rating-beer-pint{
    margin: auto;
    height: 7vw;
}

.add-review-form{
    margin: 2vw;
}

</style>