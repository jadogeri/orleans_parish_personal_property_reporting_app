
package com.opao.pp_api.features.user_change_type;
 
import com.opao.pp_api.features.user_change_type.model.UserChangeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserChangeTypeRepository extends JpaRepository<UserChangeTypeEntity, Integer> {

    // Note: Standard findAll() is already provided by JpaRepository

    // SELECT u FROM UserChangeType u WHERE u.userChangeTypeId = :userChangeTypeId
    Optional<UserChangeTypeEntity> findByUserChangeTypeId(Integer userChangeTypeId);

    // SELECT u FROM UserChangeType u WHERE u.name = :name
    Optional<UserChangeTypeEntity> findByName(String name);
}
