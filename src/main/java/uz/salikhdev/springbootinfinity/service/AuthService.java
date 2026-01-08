package uz.salikhdev.springbootinfinity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.salikhdev.springbootinfinity.dto.request.LoginRequest;
import uz.salikhdev.springbootinfinity.dto.request.RegisterRequest;
import uz.salikhdev.springbootinfinity.dto.response.LoginResponse;
import uz.salikhdev.springbootinfinity.entity.Role;
import uz.salikhdev.springbootinfinity.entity.State;
import uz.salikhdev.springbootinfinity.entity.User;
import uz.salikhdev.springbootinfinity.repositroy.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registration(RegisterRequest request) {
        userRepository.findByPhoneNumber(request.phoneNumber())
                .ifPresent(user -> {

                    if (user.getState().equals(State.UNVERIFIED)) {
                        // resend code
                        // TODO : sms jo'natamiz.
                    } else {
                        throw new RuntimeException("User already exists");
                    }


                });

        User user = User.builder()
                .lastName(request.lastName())
                .firstName(request.firstName())
                .phoneNumber(request.phoneNumber())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .state(State.UNVERIFIED)
                .email(request.email())
                .username(request.username())
                .isActive(true)
                .build();

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByPhoneNumber(request.phoneNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Password incorrect");
        }

        if (!user.getState().equals(State.ACTIVE)) {
            throw new RuntimeException("User is not active or not verified");
        }

        String token = UUID.randomUUID().toString();
        user.setToken(token);
        userRepository.save(user);

        return LoginResponse.builder()
                .id(user.getId())
                .role(user.getRole().name())
                .token(token)
                .build();
    }
}
