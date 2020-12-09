package com.example.SpringFlights;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
