package com.opao.pp_api.features.noa_pp_lat5_inventories;

import com.opao.pp_api.features.noa_pp_lat5_inventories.mapper.NoaPpLat5InventoriesMapper;
import com.opao.pp_api.features.noa_pp_lat5_inventories.model.NoaPpLat5Inventories;
import com.opao.pp_api.features.noa_pp_lat5_inventories.model.NoaPpLat5InventoriesEntity;
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
public class NoaPpLat5InventoriesService {

    private final NoaPpLat5InventoriesRepository repository;
    private final NoaPpLat5InventoriesMapper mapper;

    /**
     * Retrieves all inventory business domain records.
     */
    public List<NoaPpLat5Inventories> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single inventory record by its primary key.
     */
    public Optional<NoaPpLat5Inventories> findById(Integer id) {
        return repository.findByNoaPpLat5InventoriesId(id)
                .map(mapper::toDomain);
    }

    /**
     * Finds inventory records by jurisdiction.
     */
    public List<NoaPpLat5Inventories> findByJurisdiction(String jurisdiction) {
        return repository.findByJur(jurisdiction).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Finds inventory records mapped to a parcel identifier.
     */
    public List<NoaPpLat5Inventories> findByParcelId(String parcelId) {
        return repository.findByParid(parcelId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Finds inventory lines registered under a specific tax year.
     */
    public List<NoaPpLat5Inventories> findByTaxYear(Integer taxYear) {
        return repository.findByTaxyr(taxYear).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Filters assets belonging to a particular inventory categorization type.
     */
    public List<NoaPpLat5Inventories> findByInventoryType(String inventoryType) {
        return repository.findByInventoryType(inventoryType).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Filters inventory records matching a specific reporting month ledger.
     */
    public List<NoaPpLat5Inventories> findByInventoryMonth(String inventoryMonth) {
        return repository.findByInventoryMonth(inventoryMonth).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Persists a pristine new inventory domain tracking model down into the database.
     */
    @Transactional
    public NoaPpLat5Inventories create(NoaPpLat5Inventories domain) {
        NoaPpLat5InventoriesEntity entity = mapper.toEntity(domain);

        // Note: MapStruct ignores the parental 'noaPpLat5' structural link.
        // If needed, assign parent reference tracking proxy linkages here:
        // entity.setNoaPpLat5(parentEntity);

        NoaPpLat5InventoriesEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    /**
     * Updates fields cleanly on an existing database inventory entry.
     */
    @Transactional
    public Optional<NoaPpLat5Inventories> update(Integer id, NoaPpLat5Inventories domain) {
        return repository.findByNoaPpLat5InventoriesId(id).map(existingEntity -> {
            mapper.updateEntityFromDomain(domain, existingEntity);
            NoaPpLat5InventoriesEntity updatedEntity = repository.save(existingEntity);
            return mapper.toDomain(updatedEntity);
        });
    }

    /**
     * Deletes an inventory log segment permanently from storage maps.
     */
    @Transactional
    public boolean deleteById(Integer id) {
        return repository.findByNoaPpLat5InventoriesId(id).map(entity -> {
            repository.delete(entity);
            return true;
        }).orElse(false);
    }
}
