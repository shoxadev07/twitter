package uz.salikhdev.springbootinfinity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.salikhdev.springbootinfinity.dto.CreateUserDto;
import uz.salikhdev.springbootinfinity.dto.UserDto;
import uz.salikhdev.springbootinfinity.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<UserDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/with")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        UserDto dto = userService.getByEmail(email);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody CreateUserDto user) {
        userService.saveUser(user.getFirstName(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
