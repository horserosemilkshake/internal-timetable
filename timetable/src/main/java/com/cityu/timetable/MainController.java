package com.cityu.timetable;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONStringer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(path="api/v1/timetable/time-and-room/")
public class MainController {
    private final String Protocol = "https://";
    private final String ApiHost = "api.cityu.edu.hk:1443";
    private final String ApiPrefix = "/booking-panel/xp/v1";
    private final String ApiClientId = "ca163df52f434e07b930b98bb217e3e1";
    private final String ApiClientSecret = "3C23F2b9dDA547278c5284D920a1E8d5";

    @GetMapping("/{room_id}/{month}")
    public @ResponseBody ResponseEntity<String> setTimeTable(@PathVariable String room_id, @PathVariable String month){
        final String url = Protocol.concat(ApiHost).concat(ApiPrefix).concat("/rooms/").concat(room_id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("client_id", ApiClientId);
        headers.set("client_secret", ApiClientSecret);

        HttpEntity entity = new HttpEntity(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response;
    }
}
