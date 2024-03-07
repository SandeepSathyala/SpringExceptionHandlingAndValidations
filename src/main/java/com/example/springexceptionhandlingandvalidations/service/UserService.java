package com.example.springexceptionhandlingandvalidations.service;

import com.example.springexceptionhandlingandvalidations.dto.UserDto;
import com.example.springexceptionhandlingandvalidations.entity.User;
import com.example.springexceptionhandlingandvalidations.exception.ProcessingException;
import com.example.springexceptionhandlingandvalidations.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    public UserDto createUser(UserDto userDto) throws ProcessingException{
        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPhoneNumber()
        );
        User saveUser = userRepo.save(user);
        UserDto saveUserDto = new UserDto(
                saveUser.getId(),
                saveUser.getName(),
                saveUser.getEmail(),
                saveUser.getPhoneNumber()
        );
        return saveUserDto;
    }
    public UserDto updateUser(long id, UserDto userDto) throws ProcessingException{
        Optional<User> userOptional = userRepo.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPhoneNumber(userDto.getPhoneNumber());

            User updatedUser = userRepo.save(user);

            return new UserDto(
                    updatedUser.getId(),
                    updatedUser.getName(),
                    updatedUser.getEmail(),
                    updatedUser.getPhoneNumber()
            );
        } else {
            throw new ProcessingException("User with id " + id + " does not exist");
        }
    }
    public UserDto getUser(long id) throws ProcessingException {
        Optional<User> userOptional = userRepo.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDto userDto = new UserDto(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhoneNumber()
            );
            return userDto;
        } else {
            throw new ProcessingException("User with id " + id + " does not exist");
        }
    }
    public List<UserDto> getAllUsers() throws ProcessingException{
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDtos.add(userDto);
        }

        return userDtos;
    }

    public void deleteUser(long id) throws ProcessingException{
        User user = userRepo.findById(id).orElseThrow();
        if (user != null) {
            userRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }
    }
}
