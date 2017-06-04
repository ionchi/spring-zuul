package it.uniroma3;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GatewayController {

    @RequestMapping(value = "/")
    public String mainPage() {
        return "index";
    }

    @RequestMapping(value = "/addmovie")
    public String addMovieForm(Model model) {
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "addMovie";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getMovie(@PathVariable Long id, Model model) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Movie film = mapper.readValue(new URL("http://localhost:8080/movie/"+id), Movie.class);
            model.addAttribute("film", film);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return "movieDetail";
        }
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String getAllMovies(Model model) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Movie> movieList = mapper.
                    readValue(new URL("http://localhost:8080/movie/"), new TypeReference<List<Movie>>(){});
            model.addAttribute("movies", movieList);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return "movieList";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String getAllMovies(@PathVariable Long id, Model model) throws Exception {
        URL url = new URL("http://localhost:8080/movie/"+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.getResponseCode();
        connection.disconnect();
        return this.getAllMovies(model);
    }

    @RequestMapping(value = "/addmovie", method = RequestMethod.POST)
    public String addMovie(@ModelAttribute(value="movie") Movie movie, Model model) throws Exception {
        String url="http://localhost:8080/movie/";
        URL object=new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("content-type","application/json; charset=utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        JSONObject data = new JSONObject();
        data.put("name", movie.getName());
        data.put("director", movie.getDirector());
        data.put("rating", movie.getRating());
        wr.write(data.toString());
        wr.close();

        // read the response
        InputStream in = new BufferedInputStream(con.getInputStream());
        new BufferedReader(new InputStreamReader(in)) .lines().collect(Collectors.joining("\n"));

        in.close();
        con.disconnect();

        model.addAttribute("film", movie);
        return "movieDetail";
    }

}
