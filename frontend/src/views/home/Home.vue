<template>
  <section>

    <div id="home-grid">
      <div id="brewery-list-button" class="container-home container-blur">
        <div>
          <router-link class="link-in-black" v-bind:to="{ name: 'breweries-display' }">Brewery List</router-link>
        </div>
      </div>    
      <div id="featured-beer-button" class="container-home container-blur">
        <div>
          <router-link class="link-in-black" v-bind:to="{ name: 'featured-beer' }">Featured Beer</router-link>
        </div>
      </div>
      <div id="events-button" class="container-home container-blur">
        <router-link class="link-in-black" v-bind:to="{ name: 'events' }">Brewery Events</router-link>
      </div>
    </div>
  </section>
</template>



<script>
export default {
  name: "home"
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap');

.container-home {
    font-family: 'Poppins', sans-serif;
    font-size: 3vw;
    text-align: center;
    display: block;
    border-radius: 3vw;
    padding: 3vw;
    justify-content: space-evenly;
    justify-items: center;
    align-content: space-evenly;
    align-items: center;
}

#brewery-list-button {
    grid-area: "list";
}

#featured-beer-button {
    grid-area: "beer";
}

#events-button {
    grid-area: "events";
}

#home-grid {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas: "list beer events";
    row-gap: 20px;
    column-gap: 1fr;
    align-items: center;
    justify-content: space-evenly;
    justify-items: center;
    align-content: space-evenly;
}

.link-in-black {
    color: black;
}

@media screen and (max-width: 500px){
    #home-grid{
        display: grid;
        row-gap: 20px;
        column-gap: 20px;
        align-items: center;
        justify-content: space-evenly;
        justify-items: center;
        align-content: space-evenly;
        grid-template-columns: 1fr;
        grid-template-areas: "list"
        "beer"
        "events"
        ;
    }
}

</style>
