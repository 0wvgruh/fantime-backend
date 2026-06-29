package com.fantime.fantime_backend.service;
import com.fantime.fantime_backend.repository.UserRepository;
import org.springframework.stereotype.Service;  
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fantime.fantime_backend.dto.LoginRequest;
import com.fantime.fantime_backend.dto.RegisterRequest;
import com.fantime.fantime_backend.entity.User;
import com.fantime.fantime_backend.security.JwtProvider;
import com.fantime.fantime_backend.dto.LoginResponse;
import com.fantime.fantime_backend.dto.CurrentUserResponse;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }
    
    public void register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setNickname(request.getNickname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtProvider.createToken(
            user.getId(),
            user.getEmail(),
            user.getRole()
        );

        return new LoginResponse(
            token,
            user.getEmail(),
            user.getNickname(),
            user.getRole() // 권한
        );
    }
    
    public CurrentUserResponse getCurrentUser(String token){
        Long userId = jwtProvider.getUserIdToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new CurrentUserResponse(
            user.getId(),
            user.getEmail(),
            user.getNickname(),
            user.getRole()
        );
    }
}
