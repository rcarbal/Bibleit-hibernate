package co.bibleit.microservice.dataparser.bibledataparser.controller;

import co.bibleit.microservice.dataparser.bibledataparser.bean.Configuration;
import co.bibleit.microservice.dataparser.bibledataparser.dao.BibleJSONDao;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BibleDataParserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Configuration configuration;
    @Autowired
    private Environment environment;
    @Autowired
    private BibleJSONDao doa;

    @GetMapping("/configuration")
    public Map<String, Integer> getCompleteBibleJson(){

        HashMap<String, Integer> configurationToReturn = new HashMap<>();
        configurationToReturn.put("max", configuration.getConfigurationMaximum());
        configurationToReturn.put("min", configuration.getConfigurationMinimum());
        return configurationToReturn;
    }

    @GetMapping("/bible-data-parser/bible")
    public JSONObject retrieveBibleJson(){

        JSONObject object = new JSONObject();
        object.put("bible", doa.getBible());
        object.put("port", Integer.parseInt(environment.getProperty("local.server.port")));

        logger.info("{}", object);
        return object;
    }

}
