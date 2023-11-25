package project.weather_stock_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import project.weather_stock_api.dto.request.UserRegisterRequest;
import project.weather_stock_api.dto.request.UserRequest;

import project.weather_stock_api.dto.response.UserResponse;
import project.weather_stock_api.service.AuthenticationService;

import java.io.IOException;

@RestController(value = "/users")
@Tag(name = "Clients")
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @Operation(summary = "This method is used to register the clients.")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid UserRegisterRequest userRegisterRequest) throws IOException {
        return ResponseEntity.ok(authenticationService.save(userRegisterRequest));
    }
    @Operation(summary = "This method is used to login the clients.")
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }
}
