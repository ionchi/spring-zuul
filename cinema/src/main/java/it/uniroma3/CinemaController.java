package it.uniroma3;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class CinemaController {
    private Map<String,Integer> movies = new HashMap<>();

    private void populateMovies() {
        movies.put("titanic",1);
        movies.put("jaws",2);
        movies.put("the-wolf-of-wall-street",3);
        movies.put("psycho",4);
        movies.put("the-shining",5);
        movies.put("pulp-fiction",6);
    }

    @RequestMapping(value = "/")
    public Map<String,Integer> allMovies() {
        Map<String,Integer> movies = this.movies;
        populateMovies();
        return movies;
    }

    @RequestMapping(value = "/{movie}")
    public int available(@PathVariable String movie) {
        Map<String,Integer> movies = this.movies;
        populateMovies();
        int room = -1;
        for (Map.Entry<String,Integer> entry : movies.entrySet()) {
            if(entry.getKey().equals(movie))
                room = entry.getValue();
        }
        return room;
    }

    @RequestMapping(value = "/{movie}/{room}")
    public String time(@PathVariable String movie, @PathVariable int room) {
        int hours = ThreadLocalRandom.current().nextInt(18, 22 + 1);
        int minutes = ThreadLocalRandom.current().nextInt(0, 5 + 1);
        return hours+":"+minutes+0;
    }

}
