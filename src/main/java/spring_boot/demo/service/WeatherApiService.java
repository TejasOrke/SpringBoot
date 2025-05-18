package spring_boot.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring_boot.demo.api.response.WeatherResponse;
import spring_boot.demo.cache.AppCache;

@Service
public class WeatherApiService {


    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city){
        String replace = appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString()).replace("<city>",city).replace("<apiKey>", apiKey);
        ResponseEntity<WeatherResponse> exchange = restTemplate.exchange(replace, HttpMethod.GET, null, WeatherResponse.class);
        exchange.getStatusCode().is2xxSuccessful();
        return exchange.getBody();
    }
}
