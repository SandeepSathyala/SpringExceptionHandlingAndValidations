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
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
        try {
            UserDto user = userService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (ProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") @Min(value = 1, message = "User ID must be greater than or equal to 1") long id, @Valid @RequestBody UserDto userDto) {
        try {
            UserDto updatedUserDto = userService.updateUser(id, userDto);
            return ResponseEntity.ok(updatedUserDto);
        } catch (ProcessingException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") @Min(value = 1, message = "User ID must be greater than or equal to 1") long id) {
        try {
            UserDto userDto = userService.getUser(id);
            return ResponseEntity.ok(userDto);
        } catch (ProcessingException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserDto> userDtos = userService.getAllUsers();
            return ResponseEntity.ok(userDtos);
        } catch (ProcessingException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") @Min(value = 1, message = "User ID must be greater than or equal to 1") long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User with ID " + id + " has been deleted successfully.");
        } catch (ProcessingException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
