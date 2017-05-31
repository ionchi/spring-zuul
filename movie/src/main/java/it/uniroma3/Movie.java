package it.uniroma3;

public class Movie {
    private final long id;
    private final String name;
    private final String director;
    private final double rating;

    public Movie() {
        this.id = -1;
        this.name = "default";
        this.director = "default";
        this.rating = -1;
    }

    public Movie(long id, String name, String director, double rating) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public double getRating() {
        return rating;
    }
}
