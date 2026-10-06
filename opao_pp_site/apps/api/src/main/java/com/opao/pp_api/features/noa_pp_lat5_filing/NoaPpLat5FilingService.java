package com.opao.pp_api.features.noa_pp_lat5_filing;

import com.opao.pp_api.features.noa_pp_lat5_filing.mapper.NoaPpLat5FilingMapper;
import com.opao.pp_api.features.noa_pp_lat5_filing.model.NoaPpLat5Filing;
import com.opao.pp_api.features.noa_pp_lat5_filing.model.NoaPpLat5FilingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoaPpLat5FilingService {

    private final NoaPpLat5FilingRepository repository;
    private final NoaPpLat5FilingMapper mapper;

    /**
     * Retrieves all NoaPpLat5Filing domain models.
     */
    public List<NoaPpLat5Filing> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single filing record by its internal unique database ID.
     */
    public Optional<NoaPpLat5Filing> findById(Integer id) {
        return repository.findByNoaPpLat5FilingId(id)
                .map(mapper::toDomain);
    }

    /**
     * Specialized retrieval queries utilizing your custom repository lookup methods.
     */
    public List<NoaPpLat5Filing> findByJurisdiction(String jurisdiction) {
        return repository.findByJur(jurisdiction).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<NoaPpLat5Filing> findByParcelId(String parcelId) {
        return repository.findByParid(parcelId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<NoaPpLat5Filing> findByTaxYear(Integer taxYear) {
        return repository.findByTaxyr(taxYear).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<NoaPpLat5Filing> findByCategory(String category) {
        return repository.findByCategory(category).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<NoaPpLat5Filing> findByPropertyType(String propertyType) {
        return repository.findByPptype(propertyType).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<NoaPpLat5Filing> findByAcquisitionCost(BigDecimal acquisitionCost) {
        return repository.findByAcquisitionCost(acquisitionCost).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Persists a new filing entity to the database layer.
     */
    @Transactional
    public NoaPpLat5Filing create(NoaPpLat5Filing domain) {
        NoaPpLat5FilingEntity entity = mapper.toEntity(domain);
        
        // Note: As specified in your MapStruct configuration, the parental 'noaPpLat5' 
        // entity relationship needs to be set manually here before saving if it is passed in:
        // entity.setNoaPpLat5(parentEntityProxy);

        NoaPpLat5FilingEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    /**
     * Updates an existing database filing record cleanly via a partial mapping structure.
     */
    @Transactional
    public Optional<NoaPpLat5Filing> update(Integer id, NoaPpLat5Filing domain) {
        return repository.findByNoaPpLat5FilingId(id).map(existingEntity -> {
            // Merges structural updates onto the persistent database target safely.
            mapper.updateEntityFromDomain(domain, existingEntity);
            
            NoaPpLat5FilingEntity updatedEntity = repository.save(existingEntity);
            return mapper.toDomain(updatedEntity);
        });
    }

    /**
     * Permanent record purging routine by ID. Returns true if located and removed successfully.
     */
    @Transactional
    public boolean deleteById(Integer id) {
        return repository.findByNoaPpLat5FilingId(id).map(entity -> {
            repository.delete(entity);
            return true;
        }).orElse(false);
    }
}
