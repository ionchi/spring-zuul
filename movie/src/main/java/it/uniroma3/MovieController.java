package it.uniroma3;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MovieController {
    private Map<String,String> movies = new HashMap<>();

    private void populateMovies() {
        movies.put("titanic","cameron");
        movies.put("jaws","spielberg");
        movies.put("the-wolf-of-wall-street","scorsese");
        movies.put("psycho","hitchcock");
        movies.put("the-shining","kubrick");
        movies.put("pulp-fiction","tarantino");
    }

    @RequestMapping(value = "/{movie}")
    public String available(@PathVariable String movie) {
        Map<String,String> movies = this.movies;
        populateMovies();
        String director = "not found";
        for (Map.Entry<String,String> entry : movies.entrySet()) {
            if(entry.getKey().equals(movie))
                director = entry.getValue();
        }
        return director;
    }

    @RequestMapping(value = "/")
    public Map<String,String> allMovies() {
        Map<String,String> movies = this.movies;
        populateMovies();
        return movies;
    }

}
