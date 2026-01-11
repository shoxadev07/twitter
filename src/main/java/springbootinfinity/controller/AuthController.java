package springbootinfinity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbootinfinity.dto.request.LoginRequest;
import springbootinfinity.dto.request.RegisterRequest;
import springbootinfinity.dto.request.VerifyRequest;
import springbootinfinity.dto.response.LoginResponse;
import springbootinfinity.dto.response.SuccessResponse;
import springbootinfinity.service.AuthService;

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

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody VerifyRequest request) {
        authService.verify(request);
        return ResponseEntity.ok(SuccessResponse.created("User verified successfully."));
    }
}
