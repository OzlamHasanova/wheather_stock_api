package project.weather_stock_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserRegisterRequest {
    private String username;
    private String email;
    private String password;
    private MultipartFile userImg;
}
