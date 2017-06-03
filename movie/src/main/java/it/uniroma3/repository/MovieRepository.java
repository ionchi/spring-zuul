package it.uniroma3.repository;

import it.uniroma3.model.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    List<Movie> findByName(@Param("name") String name);

    @Query("SELECT m.name FROM Movie m where m.id = :id")
    Movie findNameById(@Param("id") Long id);

    List<Movie> findAll();

}
