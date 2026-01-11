package uz.salikhdev.springbootinfinity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.salikhdev.springbootinfinity.dto.request.LoginRequest;
import uz.salikhdev.springbootinfinity.dto.request.RegisterRequest;
import uz.salikhdev.springbootinfinity.dto.request.VerifyRequest;
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
    private final SmsService smsService;

    public void registration(RegisterRequest request) {
        userRepository.findByPhoneNumber(request.phoneNumber())
                .ifPresent(user -> {

                    if (user.getState().equals(State.UNVERIFIED)) {
                       smsService.sendOtp(request.phoneNumber());

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
        smsService.sendOtp(request.phoneNumber());
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

   public void verify(VerifyRequest request) {

      User user = userRepository.findByPhoneNumber(request.phoneNumber())
      .orElseThrow(() -> new RuntimeException("User not found"));

       boolean isSucces = smsService.verifyOtp(request.phoneNumber(), request.otp());
       if (!isSucces) {
        throw new RuntimeException("Verification failed");
    }
       user.setState(State.ACTIVE);
       userRepository.save(user);


    }



}
