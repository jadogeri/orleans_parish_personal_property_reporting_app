package com.opao.pp_api.features.user_change;

import com.opao.pp_api.features.user.model.UserEntity;
import com.opao.pp_api.features.user_change.mapper.UserChangeMapper;
import com.opao.pp_api.features.user_change.model.UserChange;
import com.opao.pp_api.features.user_change.model.UserChangeEntity;
import com.opao.pp_api.features.user_change_type.model.UserChangeTypeEntity;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserChangeService {

    private final UserChangeRepository userChangeRepository;
    private final UserChangeMapper userChangeMapper;
    
    // Note: Inject respective entity managers or reference lookup handlers if required
    private final jakarta.persistence.EntityManager entityManager;

    /**
     * Creates a brand-new UserChange logging request.
     */
    @Transactional
    public UserChange create(UserChange domain) {
        UserChangeEntity entity = userChangeMapper.toEntity(domain);
        
        // Resolve complex relational entities using light JPA proxies to avoid deep fetches
        if (domain.getUserChangeTypeId() != null) {
            entity.setUserChangeTypeId(entityManager.getReference(
                UserChangeTypeEntity.class, 
                domain.getUserChangeTypeId()
            ));
        }
        if (domain.getUserId() != null) {
            entity.setUserId(entityManager.getReference(
                UserEntity.class, 
                domain.getUserId()
            ));
        }

        UserChangeEntity savedEntity = userChangeRepository.save(entity);
        return userChangeMapper.toDomain(savedEntity);
    }

    /**
     * Safely updates an existing incremental domain state into an existing persistence target.
     */
    @Transactional
    public UserChange edit(Integer id, UserChange domain) {
        UserChangeEntity existingEntity = userChangeRepository.findById(id.longValue())
            .orElseThrow(() -> new EntityNotFoundException("UserChange request with id " + id + " no longer exists."));
        
        // Use incremental MapStruct strategy ignoring primary structures
        userChangeMapper.updateEntityFromDomain(domain, existingEntity);
        
        // Handle standalone updates to relationships if they are provided in the domain payload
        if (domain.getUserChangeTypeId() != null) {
            existingEntity.setUserChangeTypeId(entityManager.getReference(
                UserChangeTypeEntity.class, 
                domain.getUserChangeTypeId()
            ));
        }
        if (domain.getUserId() != null) {
            existingEntity.setUserId(entityManager.getReference(
                UserEntity.class, 
                domain.getUserId()
            ));
        }

        UserChangeEntity updatedEntity = userChangeRepository.save(existingEntity);
        return userChangeMapper.toDomain(updatedEntity);
    }

    /**
     * Removes/Cancels a user change entry by its identity value.
     */
    @Transactional
    public void destroy(Integer id) {
        if (!userChangeRepository.existsById(id.longValue())) {
            throw new EntityNotFoundException("UserChange request with id " + id + " no longer exists.");
        }
        userChangeRepository.deleteById(id.longValue());
    }

    /**
     * Fetches details of a specific UserChange request entry by its ID.
     */
    public Optional<UserChange> findUserChange(Integer id) {
        return userChangeRepository.findById(id.longValue())
            .map(userChangeMapper::toDomain);
    }

    /**
     * Fetches details of a validation change tracking request by its token verification string.
     */
    public Optional<UserChange> findUserChangeByVerificationCode(String verificationCode) {
        return userChangeRepository.findByVerificationCode(verificationCode)
            .map(userChangeMapper::toDomain);
    }

    /**
     * Fetches a standard raw list of all existing records.
     */
    public List<UserChange> findUserChangeEntities() {
        return userChangeRepository.findAll().stream()
            .map(userChangeMapper::toDomain)
            .collect(Collectors.toList());
    }

    /**
     * Clean Spring-idiomatic replacement supporting database-driven pagination and offsets.
     */
    public Page<UserChange> findUserChangeEntities(Pageable pageable) {
        return userChangeRepository.findAll(pageable)
            .map(userChangeMapper::toDomain);
    }

    /**
     * Retrieves the structural total capacity context.
     */
    public long getUserChangeCount() {
        return userChangeRepository.count();
    }
}
