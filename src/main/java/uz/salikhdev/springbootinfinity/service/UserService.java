package uz.salikhdev.springbootinfinity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.salikhdev.springbootinfinity.dto.UserDto;
import uz.salikhdev.springbootinfinity.entity.User;
import uz.salikhdev.springbootinfinity.repositry.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(String name, String email, String password) {
        User user = User.builder()
                .firstName(name)
                .email(email)
                .password(password)
                .build();

        userRepository.save(user);
    }

    public UserDto getUser(Long id) {
        Optional<User> box = userRepository.findById(id);
        if (box.isPresent()) {

            User user = box.get();

            return UserDto.builder()
                    .id(user.getId())
                    .fullName(user.getFirstName() + " " + user.getLastName())
                    .email(user.getEmail())
                    .build();
        }
        throw new RuntimeException("User not found");
    }

    public List<UserDto> getAllUsers() {
        List<User> all = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : all) {
            UserDto uDto = UserDto.builder()
                    .id(user.getId())
                    .fullName(user.getFirstName() + " " + user.getLastName())
                    .email(user.getEmail())
                    .build();

            userDtos.add(uDto);
        }

        return userDtos;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto getByEmail(String email) {

        Optional<User> box = userRepository.findByEmail(email);

        if (box.isPresent()) {
            User user = box.get();

            return UserDto.builder()
                    .id(user.getId())
                    .fullName(user.getFirstName() + " " + user.getLastName())
                    .email(user.getEmail())
                    .build();
        }
        throw new RuntimeException("User not found");
    }
}
