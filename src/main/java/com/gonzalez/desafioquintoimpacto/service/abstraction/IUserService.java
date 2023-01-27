package com.gonzalez.desafioquintoimpacto.service.abstraction;


import com.gonzalez.desafioquintoimpacto.dto.UserDto;
import com.gonzalez.desafioquintoimpacto.model.entity.UserEntity;
import org.apache.catalina.UserDatabase;

import java.util.List;

public interface IUserService {

    void createUser (UserDto userDto);

    void delete (String id) throws Exception;

    UserDto updateUser( String id,String name, String email) throws Exception;

    UserDto getByEmail (String email);

    UserDto getById (String id);

    List<UserDto> getAll();

    UserDto entityToDto (UserEntity userEntity);


}
