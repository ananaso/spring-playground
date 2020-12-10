package com.example.SpringFlights;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Flight {
    private LocalDateTime departureDateTime;
    private HashSet<Ticket> tickets = new HashSet<>();

    @JsonGetter("Departs")
    public String getDeparts() {
        return departureDateTime.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm"));
    }

    @JsonGetter("Tickets")
    public HashSet<Ticket> getTickets() {
        return this.tickets;
    }

    public void setDeparts(int year, int month, int date, int hour, int minute) {
        this.departureDateTime = LocalDateTime.of(year, month, date, hour, minute);
    }

    public void addTicket(int price, String firstName, String lastName) {
        Person passenger = new Person(firstName, lastName);
        Ticket ticket = new Ticket(price, passenger);
        this.tickets.add(ticket);
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = new HashSet<>(tickets);
    }

    public Map<String, Integer> totalTickets() {
        Map<String, Integer> total = new HashMap<>();
        int sum = 0;
        for (Ticket ticket : tickets) {
            sum += ticket.price;
        }
        total.put("result", sum);
        return total;
    }

    private static class Person {
        private final String lastName;
        private final String firstName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @JsonGetter("LastName")
        public String getLastName() {
            return lastName;
        }

        @JsonGetter("FirstName")
        public String getFirstName() {
            return firstName;
        }
    }

    public static class Ticket {
        private final int price;
        private final Person passenger;

        public Ticket(int price, Person passenger) {
            this.price = price;
            this.passenger = passenger;
        }

        @JsonGetter("Price")
        public int getPrice() {
            return price;
        }

        @JsonGetter("Passenger")
        public Person getPassenger() {
            return passenger;
        }
    }
}



