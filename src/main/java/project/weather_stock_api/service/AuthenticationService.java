package project.weather_stock_api.service;

import project.weather_stock_api.dto.request.UserRegisterRequest;
import project.weather_stock_api.dto.request.UserRequest;
import project.weather_stock_api.dto.response.UserResponse;

public interface AuthenticationService {
    UserResponse register(UserRegisterRequest userRegisterRequest);

    UserResponse login(UserRequest userRequest);
}
