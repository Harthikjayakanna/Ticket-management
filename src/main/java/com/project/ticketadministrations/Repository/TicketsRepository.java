package com.project.ticketadministrations.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.ticketadministrations.DTO.Tickets;

public interface TicketsRepository extends JpaRepository<Tickets, Integer> {
    @Query("select t from Tickets t where t.openUser.id=?1")
    public List<Tickets> findByOpeneduser(int id);
    @Query("select t from Tickets t where t.closedUser.id=?1")
    public List<Tickets> findByClosedUser(int id);
}
