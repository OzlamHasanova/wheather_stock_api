package project.weather_stock_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.weather_stock_api.entity.Weather;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface WeatherRepository extends JpaRepository<Weather,String> {
Optional<Weather> findFirstByRequestedCityNameOrderByUpdateTimeDesc(String city);
List<Weather> findAllByRequestedCityNameOrderByUpdateTimeDesc(String city);

}
