package com.opao.pp_api.features.business_type;

import com.opao.pp_api.features.business_type.mapper.BusinessTypeMapper;
import com.opao.pp_api.features.business_type.model.BusinessType;
import com.opao.pp_api.features.business_type.model.BusinessTypeEntity;
import com.opao.pp_api.common.exceptions.ResourceNotFoundException; // Ensure this import matches your exception package location
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BusinessTypeService Unit Tests")
class BusinessTypeServiceTest {

    @Mock
    private BusinessTypeRepository repository;

    @Mock
    private BusinessTypeMapper mapper;

    @InjectMocks
    private BusinessTypeService service;

    // ─────────────────────────────────────────────────────────────────────────
    // 🟢 1. HAPPY PATHS (Expected Successful Operations)
    // ─────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Happy Path Scenarios")
    class HappyPaths {

        @Test
        @DisplayName("Should successfully persist a new business type when code is unique")
        void createBusinessType_Success() {
            BusinessType inputDomain = BusinessType.builder().code(101).description("Retail").build();
            BusinessTypeEntity mockEntity = new BusinessTypeEntity();
            
            when(repository.findByBusinessCode(101)).thenReturn(Optional.empty());
            when(mapper.toEntity(inputDomain)).thenReturn(mockEntity);
            when(repository.save(mockEntity)).thenReturn(mockEntity);
            when(mapper.toDomain(mockEntity)).thenReturn(inputDomain);

            BusinessType result = service.createBusinessType(inputDomain);

            assertNotNull(result);
            assertEquals(101, result.getCode());
            verify(repository, times(1)).save(mockEntity);
        }

        @Test
        @DisplayName("Should successfully delete a business type if it exists in the database")
        void deleteBusinessType_Success() {
            // Arrange
            when(repository.existsById(1)).thenReturn(true);

            // Act & Assert
            // 💡 FIX: Assert that a void method executes cleanly without throwing any errors
            assertDoesNotThrow(() -> service.deleteBusinessType(1));
            
            verify(repository, times(1)).deleteById(1);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 🟡 2. EDGE CASES (Boundary Conditions and Unexpected Inputs)
    // ─────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Edge Case Scenarios")
    class EdgeCases {

        @Test
        @DisplayName("Should return an empty list gracefully when no records exist in the database")
        void getAllBusinessTypes_ReturnsEmptyList() {
            when(repository.findAll()).thenReturn(Collections.emptyList());

            List<BusinessType> result = service.getAllBusinessTypes();

            assertNotNull(result);
            assertTrue(result.isEmpty());
            verify(mapper, never()).toDomain(any());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 🔴 3. EXCEPTION CASES (Business Violations and Error Conditions)
    // ─────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Exception Case Scenarios")
    class ExceptionCases {

        @Test
        @DisplayName("Should throw IllegalArgumentException when registering a duplicate code")
        void createBusinessType_ThrowsExceptionForDuplicateCode() {
            BusinessType duplicateDomain = BusinessType.builder().code(101).description("Wholesale").build();
            BusinessTypeEntity existingEntity = new BusinessTypeEntity();
            
            when(repository.findByBusinessCode(101)).thenReturn(Optional.of(existingEntity));

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                service.createBusinessType(duplicateDomain);
            });

            assertEquals("Business code is already registered", exception.getMessage());
            verify(repository, never()).save(any());
        }

        @Test
        @DisplayName("Should return empty Optional when updating a non-existent ID context")
        void updateBusinessType_ReturnsEmptyForMissingId() {
            BusinessType updateInput = BusinessType.builder().code(102).description("Manufacturing").build();
            when(repository.findById(999)).thenReturn(Optional.empty());

            Optional<BusinessType> result = service.updateBusinessType(999, updateInput);

            assertTrue(result.isEmpty());
            verify(repository, never()).save(any());
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when trying to delete an ID that does not exist")
        void deleteBusinessType_ThrowsResourceNotFoundException() {
            // Arrange
            // 💡 FIX: Moved from "EdgeCases" to "ExceptionCases" because missing IDs now throw explicit exceptions
            when(repository.existsById(999)).thenReturn(false);

            // Act & Assert
            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
                service.deleteBusinessType(999);
            });

            assertEquals("Business type record with ID 999 does not exist.", exception.getMessage());
            verify(repository, never()).deleteById(any());
        }
    }
}
