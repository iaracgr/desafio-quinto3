package com.gonzalez.desafioquintoimpacto.mapper;

import com.gonzalez.desafioquintoimpacto.dto.UserDto;
import com.gonzalez.desafioquintoimpacto.model.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

    public UserDto map(UserEntity user){
        UserDto userResponse = new UserDto();
        userResponse.setId(user.getUserId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setSoftDelete(user.getSoftDelete());
        return userResponse;
    }

    public List<UserDto> map(List<UserEntity> users) {
        List<UserDto> responses = new ArrayList<>(users.size());
        for (UserEntity user : users) {
            responses.add(map(user));
        }
        return responses;
    }

}
