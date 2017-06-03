package it.uniroma3.controller;


import it.uniroma3.model.Movie;
import it.uniroma3.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieRepository repository;

    @Inject
    public void setRepository(MovieRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(repository.save(movie), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Movie>> getAllMovies() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Movie> getMovieWithId(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(params = {"name"}, method = RequestMethod.GET)
    public ResponseEntity<Collection<Movie>> findMovieWithName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(repository.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Movie> updateMovieFromDB(@PathVariable("id") long id, @RequestBody Movie movie) {

        Movie currentMovie = repository.findOne(id);
        currentMovie.setName(movie.getName());
        currentMovie.setDirector(movie.getDirector());
        currentMovie.setRating(movie.getRating());

        return new ResponseEntity<>(repository.save(currentMovie), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMovieWithId(@PathVariable Long id) {
        repository.delete(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllMovies() {
        repository.deleteAll();
    }
}