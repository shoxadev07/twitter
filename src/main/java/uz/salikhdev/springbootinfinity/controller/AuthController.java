package uz.salikhdev.springbootinfinity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.springbootinfinity.dto.request.LoginRequest;
import uz.salikhdev.springbootinfinity.dto.request.RegisterRequest;
import uz.salikhdev.springbootinfinity.dto.response.LoginResponse;
import uz.salikhdev.springbootinfinity.dto.response.SuccessResponse;
import uz.salikhdev.springbootinfinity.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authService.registration(request);
        return ResponseEntity.ok(SuccessResponse.created("User registered successfully."));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
