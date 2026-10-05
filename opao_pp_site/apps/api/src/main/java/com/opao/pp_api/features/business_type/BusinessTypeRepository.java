package com.opao.pp_api.features.business_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opao.pp_api.features.business_type.model.BusinessTypeEntity;

import java.util.Optional;

@Repository 
public interface BusinessTypeRepository extends JpaRepository<BusinessTypeEntity, Integer> {
    Optional<BusinessTypeEntity> findByBusinessCode(Integer businessCode);
    Optional<BusinessTypeEntity> findByBusinessDescription(String businessDescription);
    Optional<BusinessTypeEntity> findByBusinessTypeId(Integer businessTypeId);
}
