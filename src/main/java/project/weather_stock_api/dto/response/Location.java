package project.weather_stock_api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Location{
      private   String name;
      private   String country;
      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
      private   String localtime;
      private   String lat;
      private   String lon;
      private   String timezone_id;
      private   Integer localtime_epoch;
      private   String utc_offset;

}
