package com.opao.pp_api.features.form_type;
 
import com.opao.pp_api.features.form_type.model.FormTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormTypeRepository extends JpaRepository<FormTypeEntity, Integer> {

    // Note: Standard findAll() is already provided by JpaRepository

    // SELECT f FROM FormType f WHERE f.formTypeId = :formTypeId
    Optional<FormTypeEntity> findByFormTypeId(Integer formTypeId);

    // SELECT f FROM FormType f WHERE f.formName = :formName
    Optional<FormTypeEntity> findByFormName(String formName);
}
