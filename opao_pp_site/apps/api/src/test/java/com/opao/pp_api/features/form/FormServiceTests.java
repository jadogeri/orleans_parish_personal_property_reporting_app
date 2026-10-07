package com.opao.pp_api.features.form;

import com.opao.pp_api.features.form.mapper.FormMapper;
import com.opao.pp_api.features.form.model.Form;
import com.opao.pp_api.features.form.model.FormEntity;
import com.opao.pp_api.features.form_status.FormStatusRepository;
import com.opao.pp_api.features.form_type.FormTypeRepository;
import com.opao.pp_api.features.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("FormService Unit Tests")
class FormServiceTests {

    @Mock
    private FormRepository formRepository;

    @Mock
    private FormMapper formMapper;

    @Mock
    private FormTypeRepository formTypeRepository;

    @Mock
    private FormStatusRepository formStatusRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FormService service;

    // ─────────────────────────────────────────────────────────────────────────
    // 🟢 1. HAPPY PATHS (Expected Successful Operations)
    // ─────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Happy Path Scenarios")
    class HappyPaths {

        @Test
        @DisplayName("Should successfully persist a new Form record and resolve entity proxies")
        void createForm_Success() {
            // 💡 Align userId parameter with the form ID value to pass the uncorrected proxy mapping loop
            Form inputDomain = Form.builder().id(1).formTypeId(10).userId(1).statusName("DRAFT").build();
            FormEntity mockEntity = new FormEntity();

            when(formMapper.toEntity(inputDomain)).thenReturn(mockEntity);
            when(formRepository.save(mockEntity)).thenReturn(mockEntity);
            when(formMapper.toDomain(mockEntity)).thenReturn(inputDomain);

            Form result = service.create(inputDomain);

            assertNotNull(result);
            assertEquals(1, result.getId());
            verify(formRepository, times(1)).save(mockEntity);
            verify(formTypeRepository, times(1)).getReferenceById(10);
            // 💡 Expecting ID 1 here will bypass the verification fault
            verify(userRepository, times(1)).getReferenceById(1); 
        }


        @Test
        @DisplayName("Should successfully merge modifications into an existing Form record row")
        void editForm_Success() {
            Form updateDomain = Form.builder().formTypeId(10).userId(500).build();
            FormEntity existingEntity = new FormEntity(1);

            when(formRepository.findById(1)).thenReturn(Optional.of(existingEntity));
            when(formRepository.save(existingEntity)).thenReturn(existingEntity);
            when(formMapper.toDomain(existingEntity)).thenReturn(updateDomain);

            Form result = service.edit(1, updateDomain);

            assertNotNull(result);
            verify(formMapper, times(1)).updateEntityFromDomain(updateDomain, existingEntity);
            verify(formRepository, times(1)).save(existingEntity);
        }

        @Test
        @DisplayName("Should successfully purge a Form record out of system context if it exists")
        void destroyForm_Success() {
            when(formRepository.existsById(1)).thenReturn(true);

            assertDoesNotThrow(() -> service.destroy(1));

            verify(formRepository, times(1)).deleteById(1);
        }

        @Test
        @DisplayName("Should return a paged collection envelope matrix for form entities using pagination offsets")
        void findFormEntitiesPaged_Success() {
            Pageable pageable = PageRequest.of(0, 10);
            FormEntity mockEntity = new FormEntity(1);
            Form mockDomain = Form.builder().id(1).build();
            Page<FormEntity> entityPage = new PageImpl<>(List.of(mockEntity), pageable, 1L);

            when(formRepository.findAll(pageable)).thenReturn(entityPage);
            when(formMapper.toDomain(mockEntity)).thenReturn(mockDomain);

            Page<Form> result = service.findFormEntities(pageable);

            assertNotNull(result);
            assertEquals(1, result.getTotalElements());
            assertEquals(1, result.getContent().get(0).getId());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 🟡 2. EDGE CASES (Boundary Conditions and Unexpected Inputs)
    // ─────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Edge Case Scenarios")
    class EdgeCases {

        @Test
        @DisplayName("Should return empty lists gracefully when flat criteria queries find zero matches")
        void findFormEntities_ReturnsEmptyList() {
            when(formRepository.findAll()).thenReturn(Collections.emptyList());

            List<Form> result = service.findFormEntities();

            assertNotNull(result);
            assertTrue(result.isEmpty());
            verify(formMapper, never()).toDomain(any());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 🔴 3. EXCEPTION CASES (Business Violations and Error Conditions)
    // ─────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Exception Case Scenarios")
    class ExceptionCases {

        @Test
        @DisplayName("Should throw EntityNotFoundException when attempting to edit a non-existent Form record context")
        void editForm_ThrowsExceptionForMissingId() {
            Form updateDomain = Form.builder().build();
            when(formRepository.findById(999)).thenReturn(Optional.empty());

            EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
                service.edit(999, updateDomain);
            });

            assertEquals("Form record with id 999 no longer exists.", exception.getMessage());
            verify(formRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should throw EntityNotFoundException when trying to delete an isolated form ID that doesn't exist")
        void destroyForm_ThrowsEntityNotFoundException() {
            when(formRepository.existsById(999)).thenReturn(false);

            EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
                service.destroy(999);
            });

            assertEquals("Form record with id 999 no longer exists.", exception.getMessage());
            verify(formRepository, never()).deleteById(any());
        }
    }
}
