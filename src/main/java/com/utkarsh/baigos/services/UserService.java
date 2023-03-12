package com.utkarsh.baigos.services;

import com.utkarsh.baigos.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    UserDto getUserById(int userId);
    List<UserDto> getAllUsers();
    void deleteUser(int userId);
}
