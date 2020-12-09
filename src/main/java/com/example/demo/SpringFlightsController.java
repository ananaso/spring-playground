package com.example.demo;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/flights")
public class SpringFlightsController {
    @GetMapping("/flight")
    public Flight getFlight() {
        Flight flight = new Flight();
        flight.setDeparts(2017, 4, 21, 14, 34);
        return flight;
    }

    private static class Flight {
        private LocalDateTime departureDateTime;

        @JsonGetter("departs")
        public String getDeparts() {
            return departureDateTime.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm"));
        }

        public void setDeparts(int year, int month, int date, int hour, int minute) {
            String dateString = String.format("%d-%02d-%02d %02d:%02d", year, month, date, hour, minute);
            this.departureDateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }
}
