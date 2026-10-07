package com.opao.pp_api.features.business_type;

/**
 * @author Joseph Adogeri
 * @since 06-OCT-2026
 * @version 1.0.6
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opao.pp_api.common.exceptions.GlobalExceptionHandler;
import com.opao.pp_api.features.business_type.dto.response.BusinessTypeResponse;
import com.opao.pp_api.features.business_type.mapper.BusinessTypeDtoMapper;
import com.opao.pp_api.features.business_type.model.BusinessType;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BusinessTypeController Standalone Unit Tests")
class BusinessTypeControllerTests {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private BusinessTypeService service;

    @Mock
    private BusinessTypeDtoMapper dtoMapper;

    @InjectMocks
    private BusinessTypeController controller;

    @BeforeEach
    void setUp() {
        // 💡 Manually build MockMvc and explicitly wire up your secure GlobalExceptionHandler advice
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 🟢 1. HAPPY PATH SCENARIOS
    // ─────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Happy Path API Scenarios")
    class HappyPaths {

        @Test
        @DisplayName("GET /api/v1/business-types - Should return 200 OK with populated array matrix")
        void getAll_Success() throws Exception {
            // Arrange
            BusinessType domain = BusinessType.builder().id(1).code(10).description("Retail").build();
            BusinessTypeResponse response = new BusinessTypeResponse(1, 10, "Retail");

            when(service.getAllBusinessTypes()).thenReturn(List.of(domain));
            when(dtoMapper.toResponse(domain)).thenReturn(response);

            // Act & Assert
            mockMvc.perform(get("/api/v1/business-types")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].code").value(10))
                    .andExpect(jsonPath("$[0].description").value("Retail"));
        }

        @Test
        @DisplayName("GET /api/v1/business-types/{id} - Should return 200 OK when record exists")
        void getById_Success() throws Exception {
            // Arrange
            BusinessType domain = BusinessType.builder().id(1).code(10).description("Retail").build();
            BusinessTypeResponse response = new BusinessTypeResponse(1, 10, "Retail");

            when(service.getBusinessTypeById(1)).thenReturn(Optional.of(domain));
            when(dtoMapper.toResponse(domain)).thenReturn(response);

            // Act & Assert
            mockMvc.perform(get("/api/v1/business-types/1")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.code").value(10))
                    .andExpect(jsonPath("$.description").value("Retail"));
        }

        @Test
        @DisplayName("DELETE /api/v1/business-types/{id} - Should return 204 No Content upon total delete success")
        void delete_Success() throws Exception {
            // Arrange
            doNothing().when(service).deleteBusinessType(1);

            // Act & Assert
            mockMvc.perform(delete("/api/v1/business-types/1"))
                    .andExpect(status().isNoContent());

            verify(service, times(1)).deleteBusinessType(1);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 🔴 2. EXCEPTION SCENARIOS
    // ─────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Exception and Error Contract Diagnostics")
    class ExceptionPaths {

        @Test
        @DisplayName("GET /api/v1/business-types/{id} - Should emit secure generic contract when ID drops on 404")
        void getById_NotFound_ReturnsSecureContract() throws Exception {
            // Arrange
            when(service.getBusinessTypeById(999)).thenReturn(Optional.empty());

            // Act & Assert
            mockMvc.perform(get("/api/v1/business-types/999")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.code").value("RESOURCE_NOT_FOUND"))
                    .andExpect(jsonPath("$.status").value(404))
                    .andExpect(jsonPath("$.trackingId").exists())
                    .andExpect(jsonPath("$.timestamp").exists())
                    .andExpect(jsonPath("$.method").doesNotExist()) // ➔ Shielder validation check passing
                    .andExpect(jsonPath("$.url").doesNotExist());
        }
    }
}
