package springbootinfinity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springbootinfinity.dto.request.LoginRequest;
import springbootinfinity.dto.request.RegisterRequest;
import springbootinfinity.dto.request.VerifyRequest;
import springbootinfinity.dto.response.LoginResponse;
import springbootinfinity.entity.Role;
import springbootinfinity.entity.State;
import springbootinfinity.entity.User;
import springbootinfinity.repositroy.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SmsService smsService;

    public void registration(RegisterRequest request) {
        Optional<User> optUSer = userRepository.findByPhoneNumber(request.phoneNumber());
        if (optUSer.isPresent()) {
            User user = optUSer.get();
            if (user.getState().equals(State.UNVERIFIED)) {
                smsService.sendOtp(request.phoneNumber());
               return;
            } else {
                throw new RuntimeException("User already exists");
            }
        }






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
