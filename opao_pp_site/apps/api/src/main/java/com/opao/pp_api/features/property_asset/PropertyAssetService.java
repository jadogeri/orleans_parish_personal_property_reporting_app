package com.opao.pp_api.features.property_asset;

import com.opao.pp_api.features.property_asset.mapper.PropertyAssetMapper;
import com.opao.pp_api.features.property_asset.model.PropertyAsset;
import com.opao.pp_api.features.property_asset.model.PropertyAssetEntity;
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
public class PropertyAssetService {

    private final PropertyAssetRepository propertyAssetRepository;
    private final PropertyAssetMapper propertyAssetMapper;

    /**
     * Creates a brand-new PropertyAsset logging record.
     */
    @Transactional
    public PropertyAsset create(PropertyAsset domain) {
        PropertyAssetEntity entity = propertyAssetMapper.toEntity(domain);
        PropertyAssetEntity savedEntity = propertyAssetRepository.save(entity);
        return propertyAssetMapper.toDomain(savedEntity);
    }

    /**
     * Safely updates an existing PropertyAsset record.
     */
    @Transactional
    public PropertyAsset edit(Integer id, PropertyAsset domain) {
        PropertyAssetEntity existingEntity = propertyAssetRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("PropertyAsset with id " + id + " no longer exists."));
        
        propertyAssetMapper.updateEntityFromDomain(domain, existingEntity);
        PropertyAssetEntity updatedEntity = propertyAssetRepository.save(existingEntity);
        return propertyAssetMapper.toDomain(updatedEntity);
    }

    /**
     * Removes a PropertyAsset entry by its identity value.
     */
    @Transactional
    public void destroy(Integer id) {
        if (!propertyAssetRepository.existsById(id)) {
            throw new EntityNotFoundException("PropertyAsset with id " + id + " no longer exists.");
        }
        propertyAssetRepository.deleteById(id);
    }

    /**
     * Fetches details of a specific PropertyAsset entry by its ID.
     */
    public Optional<PropertyAsset> findPropertyAsset(Integer id) {
        return propertyAssetRepository.findById(id)
            .map(propertyAssetMapper::toDomain);
    }

    /**
     * Fetches a raw list of all existing records.
     */
    public List<PropertyAsset> findPropertyAssetEntities() {
        return propertyAssetRepository.findAll().stream()
            .map(propertyAssetMapper::toDomain)
            .collect(Collectors.toList());
    }

    /**
     * Enhances finding entities to support clean framework-driven pagination.
     */
    public Page<PropertyAsset> findPropertyAssetEntities(Pageable pageable) {
        return propertyAssetRepository.findAll(pageable)
            .map(propertyAssetMapper::toDomain);
    }

    /**
     * Finds property assets matching a precise Section Number parameter ordered by ID.
     */
    public List<PropertyAsset> findPropertyAssetBySectionNumber(String sectionNumber) {
        return propertyAssetRepository.findBySectionNumberOrderByPropertyAssetIdAsc(sectionNumber).stream()
            .map(propertyAssetMapper::toDomain)
            .collect(Collectors.toList());
    }

    /**
     * Finds property assets matching a precise Category.
     */
    public List<PropertyAsset> findPropertyAssetByCategory(String category) {
        return propertyAssetRepository.findByCategory(category).stream()
            .map(propertyAssetMapper::toDomain)
            .collect(Collectors.toList());
    }

    /**
     * Finds property assets matching a precise PP Type.
     */
    public List<PropertyAsset> findPropertyAssetByPptype(String pptype) {
        return propertyAssetRepository.findByPptype(pptype).stream()
            .map(propertyAssetMapper::toDomain)
            .collect(Collectors.toList());
    }

    /**
     * Finds property assets matching an exact Asset Description query string.
     */
    public List<PropertyAsset> findPropertyAssetByDescription(String assetDescription) {
        return propertyAssetRepository.findByAssetDescription(assetDescription).stream()
            .map(propertyAssetMapper::toDomain)
            .collect(Collectors.toList());
    }

    /**
     * Finds property assets matching a numeric Effective Life configuration milestone.
     */
    public List<PropertyAsset> findPropertyAssetByEffectiveLife(Integer effectiveLife) {
        return propertyAssetRepository.findByEffectiveLife(effectiveLife).stream()
            .map(propertyAssetMapper::toDomain)
            .collect(Collectors.toList());
    }

    /**
     * Retrieves total available system count context.
     */
    public long getPropertyAssetCount() {
        return propertyAssetRepository.count();
    }
}
