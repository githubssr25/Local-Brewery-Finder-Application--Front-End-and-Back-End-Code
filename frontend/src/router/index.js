import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/home/Home.vue'
import Login from '../views/account/Login.vue'
import Logout from '../views/account/Logout.vue'
import Register from '../views/account/Register.vue'
import store from '../store/index'
import RegisterBrewer from '../views/account/RegisterBrewer.vue'
import BreweriesDisplay from '../views/breweries/BreweriesDisplay.vue'
import BreweryDisplay from '../views/breweries/BreweryDisplay.vue'
import BeersDisplay from '../views/beers/BeersDisplay.vue'
import BeerDisplay from '../views/beers/BeerDisplay.vue'
import BreweryReviewDisplay from '../views/breweries/BreweryReviewDisplay.vue'
import BeerReviewDisplay from '../views/beers/BeerReviewDisplay.vue'
import UserReviewsDisplay from '../views/account/UserReviewsDisplay.vue'
import MyAccountDisplay from '../views/account/MyAccountDisplay.vue'
import UserBreweriesDisplay from '../views/brewer/UserBreweriesDisplay.vue'
import FeaturedBeerDisplay from '../views/home/FeaturedBeerDisplay.vue'
import EditBreweryDisplay from '../views/brewer/EditBreweryDisplay.vue'
import AdminDisplay from '../views/admin/AdminDisplay.vue'
import AddBreweryDisplay from '../views/admin/AddBreweryDisplay.vue'
import BrewerBreweryDisplay from '../views/brewer/BrewerBreweryDisplay.vue'
import ManageBrewersDisplay from '../views/admin/ManageBrewersDisplay.vue'
import UserBeersDisplay from '../views/brewer/UserBeersDisplay.vue'
import AddBeerDisplay from '../views/brewer/AddBeerDisplay.vue'
import RequestBreweryDisplay from '../views/brewer/RequestBreweryDisplay.vue'
import Events from '../views/home/Events.vue'
import BreweryRequestsDisplay from '../views/admin/BreweryRequestsDisplay.vue'
import EditBeerDisplay from '../views/brewer/EditBeerDisplay.vue'
import BreweryEvents from '../views/breweries/BreweryEvents.vue'
import ManageBreweryEvents from '../views/brewer/ManageBreweryEvents.vue'
import EditEvent from '../views/brewer/EditEvent.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register-brewer",
      name: "register-brewer",
      component: RegisterBrewer,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/breweries",
      name: "breweries-display",
      component: BreweriesDisplay,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/breweries/:breweryId",
      name: "brewery-display",
      component: BreweryDisplay,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/breweries/:breweryId/beers',
      name: 'beers-display',
      component: BeersDisplay,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/breweries/:breweryId/beers/:beerId',
      name: 'beer-display',
      component: BeerDisplay,
      meta: {
        requiresAuth: false
      } 
    },
    {
      path: '/breweries/:breweryId/reviews',
      name: 'brewery-review-display',
      component: BreweryReviewDisplay,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/breweries/:breweryId/beers/:beerId/reviews',
      name: 'beer-review-display',
      component: BeerReviewDisplay,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/account',
      name: 'my-account-display',
      component: MyAccountDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/account/reviews',
      name: 'user-reviews-display',
      component: UserReviewsDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/account/breweries',
      name: 'user-breweries-display',
      component: UserBreweriesDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/featured-beer',
      name: 'featured-beer',
      component: FeaturedBeerDisplay,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/account/breweries/:breweryId/edit',
      name: 'edit-brewery-display',
      component: EditBreweryDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/account/admin',
      name: 'admin-display',
      component: AdminDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/account/admin/add',
      name: 'add-brewery-display',
      component: AddBreweryDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/account/breweries/:breweryId',
      name: 'brewer-brewery-display',
      component: BrewerBreweryDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/account/admin/assign',
      name: 'manage-brewers-display',
      component: ManageBrewersDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/account/breweries/:breweryId/beers',
      name: 'user-beers-display',
      component: UserBeersDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/account/breweries/:breweryId/beers/add',
      name: 'add-beer-display',
      component: AddBeerDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/account/request/',
      name: 'request-brewery-display',
      component: RequestBreweryDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/events',
      name: 'events',
      component: Events,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/account/admin/requests',
      name: 'brewery-requests-display',
      component: BreweryRequestsDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/account/breweries/:breweryId/beers/:beerId/edit',
      name: 'edit-beers-display',
      component: EditBeerDisplay,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/breweries/:breweryId/events',
      name: 'brewery-events',
      component: BreweryEvents,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/account/breweries/:breweryId/events',
      name: 'manage-brewery-events',
      component: ManageBreweryEvents,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: `/account/breweries/:breweryId/events/edit`,
      name: 'edit-event',
      component: EditEvent,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
