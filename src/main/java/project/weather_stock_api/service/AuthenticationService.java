package project.weather_stock_api.service;

import project.weather_stock_api.dto.request.UserRegisterRequest;
import project.weather_stock_api.dto.request.UserRequest;
import project.weather_stock_api.dto.response.UserResponse;

import java.io.IOException;

public interface AuthenticationService {
    UserResponse save(UserRegisterRequest userRegisterRequest) throws IOException;

    UserResponse auth(UserRequest userRequest);
}
