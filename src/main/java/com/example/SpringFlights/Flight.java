package com.example.SpringFlights;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class Flight {
    private LocalDateTime departureDateTime;
    private final HashSet<Ticket> tickets = new HashSet<>();

    @JsonGetter("departs")
    public String getDeparts() {
        return departureDateTime.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm"));
    }

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

    private static class Person {
        private final String lastName;
        private final String firstName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }
    }

    private static class Ticket {
        private final int price;
        private final Person passenger;

        public Ticket(int price, Person passenger) {
            this.price = price;
            this.passenger = passenger;
        }

        public int getPrice() {
            return price;
        }

        public Person getPassenger() {
            return passenger;
        }
    }
}



