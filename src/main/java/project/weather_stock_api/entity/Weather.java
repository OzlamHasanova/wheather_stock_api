package project.weather_stock_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity
@Table(name = "weather")
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

@Id
   @GeneratedValue(generator = "uuid2")
   @GenericGenerator(name = "uuid2",strategy = "uuid2")
    private String Id;
    private String requestedCityName;
    private String cityName;
    private  String country;
    private Integer temperature;
    @UpdateTimestamp
    private LocalDateTime updateTime;
    private LocalDateTime responseLocalTime;

    @PrePersist
    public void generateId() {
            Id = UUID.randomUUID().toString();
    }
}
