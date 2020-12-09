package com.example.SpringFlights;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void flightHasDepartureDate() throws Exception {
        this.mvc.perform(
                get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")));
    }

    @Test
    public void flightHasTickets() throws Exception {
        this.mvc.perform(
                get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Some name")))
                .andExpect(jsonPath("$.tickets[0].passenger.lastName", is("Some other name")))
                .andExpect(jsonPath("$.tickets[0].price", is(200)));
    }

    @Test
    public void getAllFlights() throws Exception {
        String flight01 = "{\"departs\":\"2017-04-21 14:34\",\"tickets\":[{\"passenger\":{\"firstName\":\"Some name\",\"lastName\":\"Some other name\"},\"price\":200}]}";
        String flight02 = "{\"departs\":\"2017-04-21 14:34\",\"tickets\":[{\"passenger\":{\"firstName\":\"Some other name\",\"lastName\":null},\"price\":400}]}";

        this.mvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.firstName", is("Some name")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.lastName", is("Some other name")))
                .andExpect(jsonPath("$[0].tickets[0].price", is(200)))
                .andExpect(jsonPath("$[1].departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].tickets[0].passenger.firstName", is("Some other name")))
                .andExpect(jsonPath("$[1].tickets[0].passenger.lastName", is(nullValue())))
                .andExpect(jsonPath("$[1].tickets[0].price", is(400)));
    }
}
