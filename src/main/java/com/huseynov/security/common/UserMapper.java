package com.huseynov.security.common;

public class UserMapper {

    public static MyUserDTO convertEntityToDTO(MyUser user) {
        MyUserDTO userDTO = new MyUserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    public static MyUser convertDTOtoEntity(MyUserDTO userDTO) {
        MyUser user = new MyUser();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());
        return user;
    }

}
