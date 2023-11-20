package project.weather_stock_api.dto.response;

import lombok.*;
import project.weather_stock_api.entity.Weather;

import java.time.LocalDateTime;

@Builder
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse{
      private   Request request;
      private Location location;
      private   Current current;

}
