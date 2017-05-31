package it.uniroma3;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {
    private List<Movie> movieList = new ArrayList<>();

    @PostConstruct
    private void init() {
        movieList.add(new Movie(1,"titanic", "cameron", 7.7));
        movieList.add(new Movie(2,"jaws", "spielberg", 8.0));
        movieList.add(new Movie(3,"the wolf of wall street", "scorsese", 8.2));
        movieList.add(new Movie(4,"psycho", "hitchcock", 8.5));
        movieList.add(new Movie(5,"the shining", "kubrick", 8.4));
        movieList.add(new Movie(5,"pulp fiction", "tarantino", 8.9));
    }

    @RequestMapping(value = "/{movie}")
    public Movie getFilm(@PathVariable String movie) {
        Movie film = new Movie();
        for (Movie x : movieList) {
            if(x.getName().equals(movie))
                film = x;
        }
        return film;
    }

    @RequestMapping(value = "/")
    public List<Movie> getMovies() {
        return this.movieList;
    }

}
