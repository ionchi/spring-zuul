package it.uniroma3;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Controller
public class GatewayController {

    @RequestMapping(value = "/")
    public String mainPage() {
        return "index";
    }

    @RequestMapping(value = "/{movie}")
    public String getMovie(@PathVariable String movie, Model model) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Movie film = mapper.readValue(new URL("http://localhost:8080/movie/"+movie), Movie.class);
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

    @RequestMapping(value = "/movies")
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

}
