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
                .andExpect(jsonPath("$.Departs", is("2017-04-21 14:34")));
    }

    @Test
    public void flightHasTickets() throws Exception {
        this.mvc.perform(
                get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.LastName", is("Some other name")))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)));
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
                .andExpect(jsonPath("$[0].Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName", is("Some other name")))
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$[1].Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", is("Some other name")))
//                .andExpect(jsonPath("$[1].Tickets[0].Passenger.LastName", is(nullValue())))
                .andExpect(jsonPath("$[1].Tickets[0].Price", is(400)));
    }
}
