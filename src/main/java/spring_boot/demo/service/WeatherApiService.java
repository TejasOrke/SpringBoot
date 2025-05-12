package spring_boot.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring_boot.demo.api.response.WeatherResponse;

@Service
public class WeatherApiService {
    private static final String apiKey = "ef6b9c3b2c40ab6c13ce8959fb077a52";
    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String replace = API.replace("CITY", city).replace("API_KEY", apiKey);
        ResponseEntity<WeatherResponse> exchange = restTemplate.exchange(replace, HttpMethod.GET, null, WeatherResponse.class);
        exchange.getStatusCode().is2xxSuccessful();
        return exchange.getBody();
    }
}
