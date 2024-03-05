package com.example.springexceptionhandlingandvalidations.controller;

import com.example.springexceptionhandlingandvalidations.dto.UserDto;
import com.example.springexceptionhandlingandvalidations.entity.User;
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
    public ResponseEntity<UserDto>craeteUser(@RequestBody UserDto userDto){
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PutMapping("updateUser/{UserId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("UserId") long id, @RequestBody UserDto userDto) {
        UserDto updatedUserDto = userService.updateUser(id, userDto);
        if (updatedUserDto != null) {
            return ResponseEntity.ok(updatedUserDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getUserById/{Userid}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("Userid") long id) {
        UserDto userDto = userService.getUser(id);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{UserId}")
    public ResponseEntity<String> deleteUser(@PathVariable("UserId") long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User with ID " + id + " has been deleted successfully.");
    }
}
