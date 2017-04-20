package it.uniroma3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GatewayController {

    @RequestMapping(value = "/")
    public String mainPage() {
        return "index";
    }
}
