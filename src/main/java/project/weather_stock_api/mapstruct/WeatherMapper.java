//package project.weather_stock_api.mapstruct;
//
//
//import org.mapstruct.Mapper;
//import project.weather_stock_api.dto.response.WeatherDto;
//import project.weather_stock_api.dto.response.WeatherResponse;
//import project.weather_stock_api.entity.Weather;
//
//@Mapper
//public interface WeatherMapper {
//   // WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);
//
////    @Mapping(target = "id", ignore = true)
////    @Mapping(target = "updateTime", source = "weatherResponse", qualifiedByName = "currentDateTime")
////    @Mapping(target = "responseLocalTime", source = "weatherResponse.location.localtime", qualifiedByName = "parseLocalDateTime")
//    Weather mapToWeather(String city, WeatherResponse weatherResponse);
//    WeatherDto mapToWeatherDto(Weather weather);
//
////    default LocalDateTime mapToCurrentDateTime(WeatherResponse weatherResponse) {
////        return LocalDateTime.now();
////    }
//
////    default LocalDateTime mapToLocalDateTime(String localtime) {
////        return LocalDateTime.parse(localtime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
////    }
//}
