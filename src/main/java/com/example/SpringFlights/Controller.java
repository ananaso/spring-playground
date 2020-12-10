package com.example.SpringFlights;

import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @PostMapping("/tickets/total")
    public Map<String, Integer> totalTickets(@RequestBody Map<String, ArrayList<Flight.Ticket>> tickets) {
        Flight flight = new Flight();
        flight.setTickets(tickets.get("tickets"));
        return flight.totalTickets();
    }
}
