package com.project.ticketadministrations.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketadministrations.DTO.ResponseStructure;
import com.project.ticketadministrations.DTO.Tickets;
import com.project.ticketadministrations.Service.TicketsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
public class TicketController {
    @Autowired
    private TicketsService service;

    @PostMapping("/ticket/{id}")
    public ResponseEntity<ResponseStructure<Tickets>> createTicket(@RequestBody Tickets t, @PathVariable int id) {
        return service.risingTicket(id, t);
    }

    @PatchMapping("/ticket/{id}")
    public ResponseEntity<ResponseStructure<Tickets>> closingTicket(@PathVariable int id, @RequestBody Tickets t) {
        return service.closingTheTicket(id, t);
    }

    @GetMapping("/ticket")
    public ResponseEntity<ResponseStructure<List<Tickets>>> getAllTickets() {
        return service.allTickets();
    }

    @GetMapping("/openTickets/{id}")
    public ResponseEntity<ResponseStructure<List<Tickets>>> getOpenTickets(@PathVariable int id) {
        return service.allOpenTickets(id);
    }

    @GetMapping("/closeTickets/{id}")
    public ResponseEntity<ResponseStructure<List<Tickets>>> getCloseTickets(@PathVariable int id) {
        return service.allCloseTickets(id);
    }

}
