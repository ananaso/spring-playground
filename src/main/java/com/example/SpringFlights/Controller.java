package com.example.SpringFlights;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/flights")
public class Controller {
    @GetMapping("/flight")
    public Flight getFlight() {
        Flight flight = new Flight();
        flight.setDeparts(2017, 4, 21, 14, 34);
        flight.addTicket(200, "Some name", "Some other name");
        return flight;
    }

    @GetMapping("")
    public ArrayList<Flight> getAllFlights() {
        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight01 = new Flight();
        flight01.setDeparts(2017, 4, 21, 14, 34);
        flight01.addTicket(200, "Some name", "Some other name");
        flights.add(flight01);
        Flight flight02 = new Flight();
        flight02.setDeparts(2017, 4, 21, 14, 34);
        flight02.addTicket(400, "Some other name", null);
        flights.add(flight02);
        return flights;
    }
}
