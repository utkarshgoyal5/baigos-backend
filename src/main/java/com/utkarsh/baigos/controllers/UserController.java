package com.utkarsh.baigos.controllers;

import com.utkarsh.baigos.payloads.ApiResponse;
import com.utkarsh.baigos.payloads.UserDto;
import com.utkarsh.baigos.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto cretaeUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(cretaeUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        UserDto updatedUserDto = userService.updateUser(userDto);
        return ResponseEntity.ok(updatedUserDto);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("user_id") Integer userId) {
        UserDto getUserDto = userService.getUserById(userId);
        return new ResponseEntity<>(getUserDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> getAllUsersDto = userService.getAllUsers();
        return new ResponseEntity<>(getAllUsersDto, HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("user_id") Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully", "2000", true), HttpStatus.OK);
    }
}
