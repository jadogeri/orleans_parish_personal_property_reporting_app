package com.opao.pp_api.features.noa_pp_lat5;

import com.opao.pp_api.common.exceptions.GlobalExceptionHandler;
import com.opao.pp_api.features.noa_pp_lat5.dto.request.NoaPpLat5CreateRequest;
import com.opao.pp_api.features.noa_pp_lat5.dto.request.NoaPpLat5UpdateRequest;
import com.opao.pp_api.features.noa_pp_lat5.dto.response.NoaPpLat5Response;
import com.opao.pp_api.features.noa_pp_lat5.mapper.NoaPpLat5DtoMapper;
import com.opao.pp_api.features.noa_pp_lat5.model.NoaPpLat5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("NoaPpLat5Controller Unit Tests")
class NoaPpLat5ControllerTests {
    @Mock private NoaPpLat5Service service;
    @Mock private NoaPpLat5DtoMapper dtoMapper;
    @InjectMocks private NoaPpLat5Controller controller;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Nested
    @DisplayName("Happy Path Scenarios")
    class HappyPaths {
        @Test
        @DisplayName("Returns an empty array when no LAT5 records exist")
        void getAllRecords_Empty() throws Exception {
            when(service.findAll()).thenReturn(List.of());

            mockMvc.perform(get("/api/v1/noa-pp-lat5").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(content().json("[]"));

            verify(service).findAll();
            verifyNoInteractions(dtoMapper);
        }

        @Test
        @DisplayName("Routes a jurisdiction search to the matching service method")
        void searchRecords_ByJurisdiction() throws Exception {
            when(service.findByJurisdiction("ORL")).thenReturn(List.of());

            mockMvc.perform(get("/api/v1/noa-pp-lat5/search").param("jurisdiction", "ORL"))
                    .andExpect(status().isOk()).andExpect(content().json("[]"));

            verify(service).findByJurisdiction("ORL");
        }

        @Test
        @DisplayName("Creates a LAT5 record")
        void createRecord_Success() throws Exception {
            NoaPpLat5 domain = NoaPpLat5.builder().taxYear(2026).build();
            when(dtoMapper.toDomain(any(NoaPpLat5CreateRequest.class))).thenReturn(domain);
            when(service.create(domain)).thenReturn(domain);

            mockMvc.perform(post("/api/v1/noa-pp-lat5")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"jurisdiction\":\"ORL\",\"parcelId\":\"P-100\",\"taxYear\":2026,\"ownerName\":\"Owner\",\"address1\":\"1 Main St\",\"cityName\":\"Gretna\",\"stateCode\":\"LA\",\"zipCode\":\"70053\",\"pin\":\"1234\",\"formId\":1,\"businessTypeId\":1}"))
                    .andExpect(status().isCreated());

            verify(service).create(domain);
        }

        @Test
        @DisplayName("Updates an existing LAT5 record")
        void updateRecord_Success() throws Exception {
            NoaPpLat5 domain = NoaPpLat5.builder().id(1).ownerName("Updated").build();
            when(dtoMapper.toResponse(domain)).thenReturn(response(1));
            when(dtoMapper.toDomain(eq(1), any(NoaPpLat5UpdateRequest.class))).thenReturn(domain);
            when(service.update(1, domain)).thenReturn(Optional.of(domain));

            mockMvc.perform(put("/api/v1/noa-pp-lat5/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"id\":1,\"ownerName\":\"Updated\"}"))
                    .andExpect(status().isOk());

            verify(service).update(1, domain);
        }

        @Test
        @DisplayName("Returns a record when its identifier exists")
        void getRecordById_Success() throws Exception {
            NoaPpLat5 domain = NoaPpLat5.builder().id(1).build();
            when(service.findById(1)).thenReturn(Optional.of(domain));
            when(dtoMapper.toResponse(domain)).thenReturn(response(1));

            mockMvc.perform(get("/api/v1/noa-pp-lat5/1"))
                    .andExpect(status().isOk());
            verify(dtoMapper).toResponse(domain);
        }

        @Test
        @DisplayName("Deletes an existing LAT5 record")
        void deleteRecord_Success() throws Exception {
            when(service.deleteById(1)).thenReturn(true);

            mockMvc.perform(delete("/api/v1/noa-pp-lat5/1"))
                    .andExpect(status().isNoContent());
        }
    }

    @Nested
    @DisplayName("Exception Case Scenarios")
    class ExceptionCases {
        @Test
        @DisplayName("Maps a missing-record exception to 404")
        void getRecordById_MissingException() throws Exception {
            when(service.findById(404)).thenThrow(new jakarta.persistence.EntityNotFoundException("missing"));

            mockMvc.perform(get("/api/v1/noa-pp-lat5/404"))
                    .andExpect(status().isNotFound());

            verifyNoInteractions(dtoMapper);
        }
    }

    @Nested
    @DisplayName("Edge Case Scenarios")
    class EdgeCases {
        @Test
        @DisplayName("Returns not found for an absent record ID")
        void getRecordById_Empty() throws Exception {
            when(service.findById(Integer.MAX_VALUE)).thenReturn(Optional.empty());

            mockMvc.perform(get("/api/v1/noa-pp-lat5/" + Integer.MAX_VALUE))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Returns not found when deleting an absent record")
        void deleteRecord_Missing() throws Exception {
            when(service.deleteById(-1)).thenReturn(false);

            mockMvc.perform(delete("/api/v1/noa-pp-lat5/-1"))
                    .andExpect(status().isNotFound());
        }
    }

    private NoaPpLat5Response response(Integer id) {
        return new NoaPpLat5Response(id, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null);
    }
}
