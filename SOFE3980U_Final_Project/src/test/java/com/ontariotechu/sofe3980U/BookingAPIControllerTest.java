package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import com.ontariotechu.sofe3980U.core.restmodels.FlightSearchDTO;
import org.springframework.http.MediaType;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingAPIController.class)
public class BookingAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/bookadd").param("operand1", "hello").param("operand2", "world"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("helloworld"));
    }

    // TODO: Write more of these search_flight tsts:

    @Test
    public void test_search_flight_1() throws Exception {
        // Create a new FlightSearchDTO object
        FlightSearchDTO fsDTO = new FlightSearchDTO();
        fsDTO.setDepartureAirport(1);
        fsDTO.setDepartureDate("01/01/2021");
        fsDTO.setRoundTrip(false);
        fsDTO.setArrivalAirport(2);

        // Convert the DTO to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String fsDTOJson = objectMapper.writeValueAsString(fsDTO);

        // Perform the POST request
        this.mvc.perform(post("/search_flights")
                .contentType(MediaType.APPLICATION_JSON)
                .content(fsDTOJson))
                .andExpect(status().isOk());
    }

    //TODO: Test 2

    //TODO: so on... 

}