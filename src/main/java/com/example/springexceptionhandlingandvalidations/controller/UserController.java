package com.example.springexceptionhandlingandvalidations.controller;

import com.example.springexceptionhandlingandvalidations.dto.UserDto;
import com.example.springexceptionhandlingandvalidations.exception.ProcessingException;
import com.example.springexceptionhandlingandvalidations.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) throws ProcessingException {
        UserDto user = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") @Min(value = 1, message = "User ID must be greater than or equal to 1") long id, @Valid @RequestBody UserDto userDto) {
        try {
            UserDto updatedUserDto = userService.updateUser(id, userDto);
            return ResponseEntity.ok(updatedUserDto);
        } catch (ProcessingException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") @Min(value = 1, message = "User ID must be greater than or equal to 1") long id) {
        try {
            UserDto userDto = userService.getUser(id);
            return ResponseEntity.ok(userDto);
        } catch (ProcessingException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() throws ProcessingException {
        List<UserDto> userDtos = userService.getAllUsers();
        return ResponseEntity.ok(userDtos);
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") @Min(value = 1, message = "User ID must be greater than or equal to 1") long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User with ID " + id + " has been deleted successfully.");
        } catch (ProcessingException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
