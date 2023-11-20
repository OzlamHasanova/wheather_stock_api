package project.weather_stock_api.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.weather_stock_api.dto.response.WeatherResponse;

@FeignClient(name = "weatherStack",url = "http://api.weatherstack.com")
public interface WeatherServiceWithFeignClient {

    @GetMapping("/current")
    WeatherResponse getWeatherResponseWithFeignClient(@RequestParam("access_key")String  API_ACCESS_KEY,@RequestParam("query")String query);
}
