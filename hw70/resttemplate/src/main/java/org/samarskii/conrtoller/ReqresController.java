package org.samarskii.conrtoller;

import org.samarskii.UsersData;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Controller
@RequestMapping("/users")
public class ReqresController {
    RestTemplate restTemplate;
    public static final String BASE_URL = "https://reqres.in/api/users?per_page=100";

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }

    @GetMapping
    public String index(Model model){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

//            UsersData usersData = restTemplate.getForObject(BASE_URL, UsersData.class);
            ResponseEntity<UsersData> exchange = restTemplate.exchange(BASE_URL, HttpMethod.GET, entity, UsersData.class);

            model.addAttribute("users", exchange.getBody().getData());
        }
        catch (Exception ex){
            String message = ex.getMessage();
        }
        return "index";
    }

}
