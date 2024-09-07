package com.project.ticketadministrations.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ticketadministrations.DTO.Tickets;
import com.project.ticketadministrations.Repository.TicketsRepository;

@Repository
public class TicketDao {
    @Autowired
    private TicketsRepository rep;

    public Tickets saveTickets(Tickets t) {
        return rep.save(t);
    }

    public Optional<Tickets> findById(int id) {
        return rep.findById(id);
    }

    public List<Tickets> findAll() {
        return rep.findAll();
    }

    public List<Tickets> findByOpenedUser(int id) {
        return rep.findByOpeneduser(id);
    }

    public List<Tickets> findbyClosedUser(int id) {
        return rep.findByClosedUser(id);
    }

}
