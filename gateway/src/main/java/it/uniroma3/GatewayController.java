package it.uniroma3;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Controller
public class GatewayController {

    @RequestMapping(value = "/")
    public String mainPage() {
        return "index";
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

}
