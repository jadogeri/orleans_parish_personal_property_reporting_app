package com.opao.pp_api.features;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("RootController Unit Tests")
class RootControllerTests {
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new RootController()).build();

    @Test
    @DisplayName("Returns the API health message")
    void home_ReturnsSuccessMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Spring Boot API is running successfully!"));
    }
}
