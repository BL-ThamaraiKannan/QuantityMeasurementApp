package com.app.quantity_measurement_app.controller;

import com.app.quantity_measurement_app.dto.QuantityDTO;
import com.app.quantity_measurement_app.dto.QuantityInputDTO;
import com.app.quantity_measurement_app.dto.QuantityMeasurementDTO;
import com.app.quantity_measurement_app.service.IQuantityMeasurementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(JacksonAutoConfiguration.class)
class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private IQuantityMeasurementService service;

    private QuantityInputDTO validCompareInput;
    private QuantityMeasurementDTO validCompareResponse;

    @BeforeEach
    void setUp() {

        QuantityDTO thisQty = new QuantityDTO();
        thisQty.setValue(1.0);
        thisQty.setUnit("FEET");
        thisQty.setMeasurementType("LengthUnit");

        QuantityDTO thatQty = new QuantityDTO();
        thatQty.setValue(12.0);
        thatQty.setUnit("INCHES");
        thatQty.setMeasurementType("LengthUnit");

        validCompareInput = new QuantityInputDTO();
        validCompareInput.setThisQuantityDTO(thisQty);
        validCompareInput.setThatQuantityDTO(thatQty);

        validCompareResponse = new QuantityMeasurementDTO();
        validCompareResponse.setThisValue(1.0);
        validCompareResponse.setThisUnit("FEET");
        validCompareResponse.setThisMeasurementType("LengthUnit");
        validCompareResponse.setThatValue(12.0);
        validCompareResponse.setThatUnit("INCHES");
        validCompareResponse.setThatMeasurementType("LengthUnit");
        validCompareResponse.setOperation("compare");
        validCompareResponse.setResultString("true");
        validCompareResponse.setError(false);
    }

    @Test
    void testCompareQuantities_Success() throws Exception {

        when(service.compareQuantities(any(QuantityInputDTO.class)))
                .thenReturn(validCompareResponse);

        mockMvc.perform(post("/api/v1/quantities/compare")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validCompareInput)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resultString").value("true"))
                .andExpect(jsonPath("$.error").value(false));

        verify(service, times(1))
                .compareQuantities(any(QuantityInputDTO.class));
    }

    @Test
    void testAddQuantities_Success() throws Exception {

        QuantityMeasurementDTO addResponse =
                new QuantityMeasurementDTO();

        addResponse.setOperation("add");
        addResponse.setResultValue(2.0);
        addResponse.setResultUnit("FEET");
        addResponse.setError(false);

        when(service.addQuantities(any(QuantityInputDTO.class)))
                .thenReturn(addResponse);

        mockMvc.perform(post("/api/v1/quantities/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validCompareInput)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultValue").value(2.0))
                .andExpect(jsonPath("$.resultUnit").value("FEET"))
                .andExpect(jsonPath("$.error").value(false));

        verify(service, times(1))
                .addQuantities(any(QuantityInputDTO.class));
    }

    @Test
    void testGetOperationHistory_Success() throws Exception {

        when(service.getOperationHistory("compare"))
                .thenReturn(
                        Collections.singletonList(validCompareResponse)
                );

        mockMvc.perform(
                        get("/api/v1/quantities/history/operation/compare"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].operation")
                        .value("compare"));

        verify(service, times(1))
                .getOperationHistory("compare");
    }

    @Test
    void testGetOperationCount_Success() throws Exception {

        when(service.getOperationCount("COMPARE"))
                .thenReturn(5L);

        mockMvc.perform(
                        get("/api/v1/quantities/count/COMPARE"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));

        verify(service, times(1))
                .getOperationCount("COMPARE");
    }
}