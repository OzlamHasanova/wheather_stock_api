package project.weather_stock_api.service.impl;

import lombok.RequiredArgsConstructor;
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
import project.weather_stock_api.repository.UserRepository;
import project.weather_stock_api.service.AuthenticationService;
import project.weather_stock_api.service.security.JwtService;

import java.io.File;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


        private final UserRepository userRepository;

        private final JwtService jwtService;

        private final AuthenticationManager authenticationManager;

        private final PasswordEncoder passwordEncoder;
        private String path="C:\\Users\\User\\OneDrive\\Pictures\\Screenshots";

        public UserResponse save(UserRegisterRequest userRegisterRequest){
            MultipartFile file = userRegisterRequest.getUserImg();
            String name = file.getOriginalFilename();
            String filePath = path + File.separator + name;

            System.out.println(name);

            User user = User.builder()
                    .username(userRegisterRequest.getUsername())
                    .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                    .email(userRegisterRequest.getEmail())
                    .userImg(filePath)
                    .role(UserRole.USER).build();
            userRepository.save(user);
            String token = jwtService.generateToken(user);
            return UserResponse.builder().token(token).build();
        }

        public UserResponse auth(UserRequest userRequest) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userRequest.getUsername(),
                    userRequest.getPassword()));
            User user=userRepository.findByEmail(userRequest.getUsername()).orElseThrow();
            String token=jwtService.generateToken(user);
            return UserResponse.builder().token(token).build();

        }

    }

