package com.utkarsh.baigos.services.impl;

import com.utkarsh.baigos.entities.User;
import com.utkarsh.baigos.payloads.UserDto;
import com.utkarsh.baigos.repositories.UserRepo;
import com.utkarsh.baigos.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utkarsh.baigos.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        userRepo.save(user);
        return this.userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        User user = userRepo.findById(userDto.getId());

        if(null == user) {
            throw new ResourceNotFoundException("User", "id", userDto.getId());
        }

        user.setId(userDto.getId());
        user.setUserName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = userRepo.save(user);

        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepo.findById(userId);

        if(null == user) {
            throw new ResourceNotFoundException("User", "id", userId);
        }

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> allUsers = userRepo.findAll();

        List<UserDto> allUsersDto = allUsers.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return allUsersDto;
    }

    @Override
    public void deleteUser(int userId) {

        User user = userRepo.findById(userId);

        if(null == user) {
            throw new ResourceNotFoundException("User", "id", userId);
        }

        userRepo.delete(user);

    }

    private User dtoToUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDto userToDto(User user) {

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
