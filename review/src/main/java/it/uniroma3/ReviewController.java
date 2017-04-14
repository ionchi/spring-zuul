package it.uniroma3;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class ReviewController {
    private Map<String,Double> movies = new HashMap<>();

    private void populateMovies() {
        movies.put("titanic",7.7);
        movies.put("jaws",8.0);
        movies.put("the-wolf-of-wall-street",8.2);
        movies.put("psycho",8.5);
        movies.put("the-shining",8.4);
        movies.put("pulp-fiction",8.9);
    }

    @RequestMapping(value = "/")
    public Map<String,Double> allMovies() {
        Map<String,Double> movies = this.movies;
        populateMovies();
        return movies;
    }

    @RequestMapping(value = "/{movie}")
    public double available(@PathVariable String movie) {
        Map<String,Double> movies = this.movies;
        populateMovies();
        double value = -1;
        for (Map.Entry<String,Double> entry : movies.entrySet()) {
            if(entry.getKey().equals(movie))
                value = entry.getValue();
        }
        return value;
    }
}
