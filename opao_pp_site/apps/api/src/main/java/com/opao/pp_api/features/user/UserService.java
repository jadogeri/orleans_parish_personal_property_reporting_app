package com.opao.pp_api.features.user;
        
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opao.pp_api.features.user.mapper.UserMapper;
import com.opao.pp_api.features.user.model.User;
import com.opao.pp_api.features.user.model.UserEntity;
import com.opao.pp_api.features.user_role.constants.UserRoles;
import com.opao.pp_api.features.user_status.constants.UserStatuses;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper; 

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(Integer id) { 
        return userRepository.findById(id.longValue()).map(userMapper::toDomain);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::toDomain);
    }

    @Transactional
    public User createUser(User domainModel) {
        if (userRepository.findByUsername(domainModel.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }
         
        // If you have a PasswordEncoder, use: passwordEncoder.encode(domainModel.getClearTextPassword())
        if (domainModel.getHashedPassword() == null && domainModel.getClearTextPassword() != null) {
            domainModel.setHashedPassword(domainModel.getClearTextPassword()); 
            domainModel.setUserRoleId(UserRoles.TAX_PREPARER);
            domainModel.setUserStatusId(UserStatuses.ENABLED);
        }
        
        UserEntity entity = userMapper.toEntity(domainModel);
        UserEntity savedEntity = userRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }


    @Transactional
    public Optional<User> updateUser(Integer id, User updatedUser) { 
        return userRepository.findById(id.longValue()).map(existingEntity -> {
            userMapper.updateEntityFromDomain(updatedUser, existingEntity);
            UserEntity savedEntity = userRepository.save(existingEntity);
            return userMapper.toDomain(savedEntity);
        });
    }

    @Transactional
    public boolean deleteUser(Integer id) { 
        if (userRepository.existsById(id.longValue())) {
            userRepository.deleteById(id.longValue());
            return true;
        }
        return false;
    }
}
