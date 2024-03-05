package com.example.springexceptionhandlingandvalidations.controller;

import com.example.springexceptionhandlingandvalidations.dto.UserDto;
import com.example.springexceptionhandlingandvalidations.entity.User;
import com.example.springexceptionhandlingandvalidations.exception.ProcessingException;
import com.example.springexceptionhandlingandvalidations.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> craeteUser(@RequestBody UserDto userDto) throws ProcessingException {
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("updateUser/{UserId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("UserId") long id, @RequestBody UserDto userDto) {
        try {
            UserDto updatedUserDto = userService.updateUser(id, userDto);
            return ResponseEntity.ok(updatedUserDto);
        } catch (ProcessingException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UserDto());
        }
    }

    @GetMapping("/getUserById/{Userid}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("UserId") long id) {
        try {
            UserDto userDto = userService.getUser(id);
            return ResponseEntity.ok(userDto); // Return 200 OK with the user details
        } catch (ProcessingException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UserDto()); // Return 404 Not Found if user with the given id is not found
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() throws ProcessingException {
        List<UserDto> userDtos = userService.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{UserId}")
    public ResponseEntity<String> deleteUser(@PathVariable("UserId") long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User with ID " + id + " has been deleted successfully.");
        } catch (ProcessingException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id + " does not exist.");
        }
    }
}
