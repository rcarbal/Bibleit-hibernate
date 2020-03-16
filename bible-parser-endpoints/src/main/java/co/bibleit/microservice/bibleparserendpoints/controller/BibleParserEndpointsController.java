package co.bibleit.microservice.bibleparserendpoints.controller;


import co.bibleit.microservice.bibleparserendpoints.bean.Configuration;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BibleParserEndpointsController {

    @Autowired
    private Configuration configuration;
    @Autowired
    private Environment environment;

    @GetMapping("/configuration")
    public Map<String, Integer> getCompleteBibleJson(){

        HashMap<String, Integer> configurationToReturn = new HashMap<>();
        configurationToReturn.put("max", configuration.getConfigurationMaximum());
        configurationToReturn.put("min", configuration.getConfigurationMinimum());
        return configurationToReturn;
    }

    @GetMapping("/bible-parser-endpoints/bible")
    public JSONObject retrieveBibleJson(){

        ResponseEntity<JSONObject> responseEntity = new RestTemplate()
                .getForEntity("http://localhost:8000/api/bible-parser-endpoints/bible",
                        JSONObject.class);

        return responseEntity.getBody();
    }
}
