package project.weather_stock_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.weather_stock_api.dto.request.UserRegisterRequest;
import project.weather_stock_api.dto.request.UserRequest;
import project.weather_stock_api.dto.response.UserResponse;
import project.weather_stock_api.entity.User;
import project.weather_stock_api.enums.UserRole;
import project.weather_stock_api.repository.UserRepository;
import project.weather_stock_api.service.AuthenticationService;
import project.weather_stock_api.service.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("hasRole('USER')")
    public UserResponse register(UserRegisterRequest userRequest) {
        User user=User.builder()
                .userName(userRequest.getUserName())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .userImg(userRequest.getUserImg())
                .role(UserRole.USER)
                .build();
        userRepository.save(user);
        var token=jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public UserResponse login(UserRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRequest.getUserName(),
                userRequest.getPassword()));
        User user=userRepository.findUserByUserName(userRequest.getUserName()).orElseThrow();
        String token=jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }
}
