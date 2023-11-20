package project.weather_stock_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.weather_stock_api.dto.request.UserRegisterRequest;
import project.weather_stock_api.dto.request.UserRequest;

import project.weather_stock_api.dto.response.UserResponse;
import project.weather_stock_api.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest userRegisterRequest){
        return ResponseEntity.ok(authenticationService.register(userRegisterRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authenticationService.login(userRequest));
    }
}
