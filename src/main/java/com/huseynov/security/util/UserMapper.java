package com.huseynov.security.util;

import com.huseynov.security.common.MyUser;
import com.huseynov.security.dto.CreateUserRequest;
import com.huseynov.security.dto.MyUserDTO;
import com.huseynov.security.dto.ResponseUser;

public class UserMapper {

    // this class have only static methods, so we don't need its constructor.
    private UserMapper() {

    }

    public static MyUserDTO convertEntityToDTO(MyUser user) {
        MyUserDTO userDTO = new MyUserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    public static MyUser convertDTOtoEntity(MyUserDTO userDTO) {
        MyUser user = new MyUser();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());
        return user;
    }

    public static MyUser convertCreateUserToEntity(CreateUserRequest requestUser) {
        MyUser user = new MyUser();
        user.setUsername(requestUser.getUsername());
        return user;
    }

    public static ResponseUser convertEntityToResponseUser(MyUser user) {
        ResponseUser responseUser = new ResponseUser();
        responseUser.setId(user.getId());
        responseUser.setUsername(user.getUsername());
        return responseUser;
    }

}
