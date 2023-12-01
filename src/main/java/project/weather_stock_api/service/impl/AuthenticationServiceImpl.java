package project.weather_stock_api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.weather_stock_api.dto.request.UserRegisterRequest;
import project.weather_stock_api.dto.request.UserRequest;
import project.weather_stock_api.dto.response.UserResponse;
import project.weather_stock_api.entity.User;
import project.weather_stock_api.enums.UserRole;
import project.weather_stock_api.exception.ExceptionConstants;
import project.weather_stock_api.exception.WeatherProjectException;
import project.weather_stock_api.repository.UserRepository;
import project.weather_stock_api.service.AuthenticationService;
import project.weather_stock_api.service.security.JwtService;

import java.io.File;
import java.util.Locale;

//i18
@Service
@RequiredArgsConstructor
@Slf4j
@PropertySource("classpath:config.properties")
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;




    @Value("${image.path}")
    private String PATH_IMAGE;

    public UserResponse save(UserRegisterRequest userRegisterRequest,String lang) {
        try{
            MultipartFile file = userRegisterRequest.getUserImg();
            String name = file.getOriginalFilename();
            String filePath = PATH_IMAGE + File.separator + name;

            User user = User.builder()
                    .username(userRegisterRequest.getUsername())
                    .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                    .email(userRegisterRequest.getEmail())
                    .userImg(filePath)
                    .role(UserRole.USER).build();
            userRepository.save(user);
            String token = jwtService.generateToken(user);
            return UserResponse.builder().token(token).build();
        }catch (WeatherProjectException ex){
            log.error(messageSource.getMessage("user.register.error", null,
                    LocaleContextHolder.getLocale()), ex);
            throw new WeatherProjectException(ExceptionConstants.INVALID_REQUEST_DATA,messageSource.getMessage("INVALID_REQUEST_DATA", null,
                    LocaleContextHolder.getLocale()));
        }

    }

    @Override
    public UserResponse auth(UserRequest userRequest,String lang) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userRequest.getUsername(),
                    userRequest.getPassword()));
            User user=userRepository.findByEmail(userRequest.getUsername()).orElseThrow();
            String token=jwtService.generateToken(user);
            return UserResponse.builder().token(token).build();

        }catch (WeatherProjectException ex){
            log.error(messageSource.getMessage("user.login.error", null,
                    LocaleContextHolder.getLocale()), ex);
            throw new WeatherProjectException(ExceptionConstants.INVALID_REQUEST_DATA,messageSource.getMessage("INVALID_REQUEST_DATA", null,
                    LocaleContextHolder.getLocale()));
        }

}
}
