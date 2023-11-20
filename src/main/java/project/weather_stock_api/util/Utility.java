package project.weather_stock_api.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Utility {
    public String sendGet(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
    public String sendPost(String url,String data){
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers= new HttpHeaders();
        headers.add("Accept","application/json");
        headers.add("Content-type","application/json");
        ResponseEntity<String> response=restTemplate.postForEntity(url,new HttpEntity<>(data,headers),String.class);
        return response.getBody();
    }
}