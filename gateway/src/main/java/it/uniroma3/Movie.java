package it.uniroma3;

public class Movie {
    private long id;
    private String name;
    private String director;
    private double rating;

    public Movie(long id, String name, String director, double rating) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.rating = rating;
    }

    public Movie() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public double getRating() {return rating; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return '{' +
                "\"name\":\"" + name + '\"' +
                ",\"director\":\"" + director + '\"' +
                ",\"rating\":\"" + rating + '\"' +
                '}';
    }
}
