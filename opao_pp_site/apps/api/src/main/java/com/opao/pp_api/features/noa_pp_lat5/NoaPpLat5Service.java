package com.opao.pp_api.features.noa_pp_lat5;

import com.opao.pp_api.features.noa_pp_lat5.mapper.NoaPpLat5Mapper;
import com.opao.pp_api.features.noa_pp_lat5.model.NoaPpLat5;
import com.opao.pp_api.features.noa_pp_lat5.model.NoaPpLat5Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoaPpLat5Service {

    private final NoaPpLat5Repository repository;
    private final NoaPpLat5Mapper mapper;

    /**
     * Retrieves all NoaPpLat5 business models.
     */
    public List<NoaPpLat5> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single record by its internal unique database ID.
     */
    public Optional<NoaPpLat5> findById(Integer id) {
        return repository.findByNoaPpLat5Id(id)
                .map(mapper::toDomain);
    }

    /**
     * Safe search variants utilizing your domain-specific repository finders.
     */
    public List<NoaPpLat5> findByJurisdiction(String jurisdiction) {
        return repository.findByJur(jurisdiction).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<NoaPpLat5> findByParcelId(String parcelId) {
        return repository.findByParid(parcelId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<NoaPpLat5> findByTaxYear(Integer taxYear) {
        return repository.findByTaxyr(taxYear).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<NoaPpLat5> findByBusinessCode(Integer businessCode) {
        return repository.findByBusinessTypeBusinessCode(businessCode).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<NoaPpLat5> findByOwnerName(String ownerName) {
        return repository.findByOwnername(ownerName).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Persists a brand new NoaPpLat5 entry to the database layer.
     */
    @Transactional
    public NoaPpLat5 create(NoaPpLat5 domain) {
        NoaPpLat5Entity entity = mapper.toEntity(domain);
        
        // Note: MapStruct ignores 'form' and 'businessType' associations.
        // You should link them cleanly here via entity.setForm(...) or entity.setBusinessType(...)
        // if those references are provided by client request properties.

        NoaPpLat5Entity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    /**
     * Updates an existing database entry smoothly using targeted application properties.
     */
    @Transactional
    public Optional<NoaPpLat5> update(Integer id, NoaPpLat5 domain) {
        return repository.findByNoaPpLat5Id(id).map(existingEntity -> {
            // Apply fields across objects safely preserving identity
            mapper.updateEntityFromDomain(domain, existingEntity);
            
            // Explicitly handles relationship synchronizations if required
            NoaPpLat5Entity updatedEntity = repository.save(existingEntity);
            return mapper.toDomain(updatedEntity);
        });
    }

    /**
     * Removes a record permanently by ID. Returns true if located and removed successfully.
     */
    @Transactional
    public boolean deleteById(Integer id) {
        return repository.findByNoaPpLat5Id(id).map(entity -> {
            repository.delete(entity);
            return true;
        }).orElse(false);
    }
}
