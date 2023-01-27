package com.gonzalez.desafioquintoimpacto.service;

import com.gonzalez.desafioquintoimpacto.config.RoleType;
import com.gonzalez.desafioquintoimpacto.dto.UserDto;
import com.gonzalez.desafioquintoimpacto.mapper.Mapper;
import com.gonzalez.desafioquintoimpacto.model.entity.UserEntity;
import com.gonzalez.desafioquintoimpacto.repository.IRoleRepository;
import com.gonzalez.desafioquintoimpacto.repository.IUserRepository;
import com.gonzalez.desafioquintoimpacto.service.abstraction.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private Mapper mapper;

    @Override
    @Transactional
    public void createUser(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRoles(List.of((iRoleRepository.findByName(RoleType.ADMIN))));
        userRepository.save(user);
    }

    @Override
    public void delete(String id) throws Exception {
        UserEntity user = userRepository.findByUserIdAndSoftDeleteFalse(id);
        user.setSoftDelete(true);
        userRepository.save(user);
    }

    @Override
    public UserDto updateUser(String id, String name, String email) throws Exception {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserEntity newUser = user.get();
            if (name != null) {
                newUser.setName(name);
            }
            if (email != null) {
                newUser.setEmail(email);
            }
            UserDto userDto = mapper.map(userRepository.save(newUser));
            return userDto;
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    public UserDto getByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }
        return mapper.map(user);
    }

    @Override
    public UserDto getById(String id) {
        UserEntity user = userRepository.findByUserIdAndSoftDeleteFalse(id);
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }
        return mapper.map(user);
    }


    private List<UserDto> buildListResponse(List<UserEntity> users) {
        List<UserDto> dto = mapper.map(users);
        return dto;
    }

    @Override
    public List<UserDto> getAll() {
        List<UserEntity> list = userRepository.findAll();
        return buildListResponse(list);
    }

    @Override
    public UserDto entityToDto(UserEntity user) {
        UserDto dto = new UserDto();
        dto.setId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        //todo:add set role
        return dto;
    }
}
