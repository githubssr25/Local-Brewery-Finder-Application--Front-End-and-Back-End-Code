package com.techelevator.dao;

import com.techelevator.model.Nutriments;
import com.techelevator.model.FeaturedBeer;
import com.techelevator.model.Objectifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Repository
public class JdbcFeaturedBeerDao implements FeaturedBeerDao {

    public String API_LINK = "https://us.openfoodfacts.org/cgi/search.pl?action=process&tagtype_0=categories&tag_contains_0=contains&tag_0=beers&json=true&page_size=1000;";

    @Override
    public FeaturedBeer getRandomBeer() {
        Random rand = new Random();
        RestTemplate restTemplate = new RestTemplate();
        List<FeaturedBeer> bigBeerList = restTemplate.getForObject(API_LINK, Objectifier.class).products;
        int rando = rand.nextInt(bigBeerList.size()-1);
        return bigBeerList.get(rando);
    }
}
