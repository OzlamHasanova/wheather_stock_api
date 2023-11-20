package project.weather_stock_api.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Data
@Getter
public class Request{
       private String type;
       private String query;
       private String language;
       private String unit;
}
