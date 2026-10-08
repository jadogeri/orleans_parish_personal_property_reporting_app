package com.opao.pp_api.features.noa_pp_lat5;

import com.opao.pp_api.features.noa_pp_lat5.mapper.NoaPpLat5Mapper;
import com.opao.pp_api.features.noa_pp_lat5.model.NoaPpLat5;
import com.opao.pp_api.features.noa_pp_lat5.model.NoaPpLat5Entity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("NoaPpLat5Service Unit Tests")
class NoaPpLat5ServiceTests {
    @Mock private NoaPpLat5Repository repository;
    @Mock private NoaPpLat5Mapper mapper;
    @InjectMocks private NoaPpLat5Service service;

    @Nested
    @DisplayName("Happy Path Scenarios")
    class HappyPaths {
        @Test
        @DisplayName("Creates and maps a LAT5 record")
        void create_Success() {
            NoaPpLat5 domain = NoaPpLat5.builder().id(4).jurisdiction("ORL").taxYear(2026).build();
            NoaPpLat5Entity entity = new NoaPpLat5Entity();
            when(mapper.toEntity(domain)).thenReturn(entity);
            when(repository.save(entity)).thenReturn(entity);
            when(mapper.toDomain(entity)).thenReturn(domain);

            assertSame(domain, service.create(domain));
            verify(repository).save(entity);
        }

        @Test
        @DisplayName("Maps records returned by a parcel search")
        void findByParcelId_Success() {
            NoaPpLat5Entity entity = new NoaPpLat5Entity();
            NoaPpLat5 domain = NoaPpLat5.builder().parcelId("P-100").build();
            when(repository.findByParid("P-100")).thenReturn(List.of(entity));
            when(mapper.toDomain(entity)).thenReturn(domain);

            assertEquals(List.of(domain), service.findByParcelId("P-100"));
        }

        @Test
        @DisplayName("Updates an existing LAT5 record")
        void update_Success() {
            NoaPpLat5Entity existing = new NoaPpLat5Entity(4);
            NoaPpLat5 input = NoaPpLat5.builder().ownerName("Updated owner").build();
            NoaPpLat5 updated = NoaPpLat5.builder().id(4).ownerName("Updated owner").build();
            when(repository.findByNoaPpLat5Id(4)).thenReturn(Optional.of(existing));
            when(repository.save(existing)).thenReturn(existing);
            when(mapper.toDomain(existing)).thenReturn(updated);

            assertEquals("Updated owner", service.update(4, input).orElseThrow().getOwnerName());
            verify(mapper).updateEntityFromDomain(input, existing);
        }
    }

    @Nested
    @DisplayName("Exception Case Scenarios")
    class ExceptionCases {
        @Test
        @DisplayName("Propagates a persistence failure when creating a LAT5 record")
        void create_RepositoryFailure() {
            NoaPpLat5 domain = NoaPpLat5.builder().taxYear(2026).build();
            NoaPpLat5Entity entity = new NoaPpLat5Entity();
            IllegalStateException failure = new IllegalStateException("database unavailable");
            when(mapper.toEntity(domain)).thenReturn(entity);
            when(repository.save(entity)).thenThrow(failure);

            assertSame(failure, assertThrows(IllegalStateException.class, () -> service.create(domain)));
            verify(mapper, never()).toDomain(any());
        }
    }

    @Nested
    @DisplayName("Edge Case Scenarios")
    class EdgeCases {
        @Test
        @DisplayName("Returns an empty collection without invoking the mapper")
        void findAll_Empty() {
            when(repository.findAll()).thenReturn(List.of());

            assertTrue(service.findAll().isEmpty());
            verifyNoInteractions(mapper);
        }

        @Test
        @DisplayName("Returns empty when an identifier is not present")
        void findById_Missing() {
            when(repository.findByNoaPpLat5Id(Integer.MAX_VALUE)).thenReturn(Optional.empty());

            assertTrue(service.findById(Integer.MAX_VALUE).isEmpty());
            verifyNoInteractions(mapper);
        }
    }
}