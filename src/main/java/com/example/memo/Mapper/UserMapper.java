package com.example.memo.Mapper;

import com.example.memo.DTO.UserDto;
import com.example.memo.DTO.UserRegisterationDto;
import com.example.memo.Entity.User;

public class UserMapper {

   

    public User userRegisterationDtoToUser(UserRegisterationDto userRegisterationDto)
    {
        if(userRegisterationDto==null) throw new NullPointerException("The UserRegisteration DTO should not be null");
        User user=new User();
        user.setName(userRegisterationDto.getName());
        user.setEmail(userRegisterationDto.getEmail());
        user.setPassword(userRegisterationDto.getPassword());
        user.setPhoneNumber(userRegisterationDto.getPhoneNumber());
        return user;

    }

    public UserDto userToUserDto(User user)
    {
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setId(user.getUserId());
        return userDto;
    }

}
