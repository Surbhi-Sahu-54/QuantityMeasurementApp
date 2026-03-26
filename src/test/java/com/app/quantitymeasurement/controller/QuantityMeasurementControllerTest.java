package com.app.quantitymeasurement.controller;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.app.quantitymeasurement.QuantityMeasurementApplication;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.enums.OperationType;

/*
 * =========================================================
 * UC17 Controller Layer Test
 * =========================================================
 *
 * Purpose:
 * Validate REST API endpoints using MockMvc.
 *
 * Flow Tested:
 * Test -> HTTP Request -> Controller -> Service -> Response DTO
 */

@SpringBootTest(classes = QuantityMeasurementApplication.class)
@AutoConfigureMockMvc
class QuantityMeasurementControllerTest {

    private static final Logger logger =
            LoggerFactory.getLogger(QuantityMeasurementControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private com.fasterxml.jackson.databind.ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        logger.info("========== Controller Test Setup ==========");
        logger.info("Initializing MockMvc and ObjectMapper");
    }

    /*
     * =========================================================
     * Test: Addition API
     * =========================================================
     *
     * Example:
     * 1 ft + 12 in = 2 ft
     */
    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testAddAPI() throws Exception {

        logger.info("=========== START TEST: Add API ===========");

        QuantityInputDTO input = new QuantityInputDTO();
        input.setValue1(1.0);
        input.setUnit1("ft");
        input.setValue2(12.0);
        input.setUnit2("in");
        input.setOperation(OperationType.ADD);

        logger.info("STEP 1: Sending POST request to /api/v1/quantities/add");

        mockMvc.perform(
                post("/api/v1/quantities/add")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value(2.0));

        logger.info("RESULT: API returned correct addition result");
        logger.info("=========== TEST PASSED ===========");
    }

    /*
     * =========================================================
     * Test: Conversion API
     * =========================================================
     */
    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testConvertAPI() throws Exception {

        logger.info("=========== START TEST: Convert API ===========");

        QuantityInputDTO input = new QuantityInputDTO();
        input.setValue1(1.0);
        input.setUnit1("ft");
        input.setTargetUnit("in");
        input.setOperation(OperationType.CONVERT);

        logger.info("STEP 1: Sending POST request to /api/v1/quantities/convert");

        mockMvc.perform(
                post("/api/v1/quantities/convert")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value(12.0));

        logger.info("RESULT: API returned correct conversion result");
        logger.info("=========== TEST PASSED ===========");
    }
}