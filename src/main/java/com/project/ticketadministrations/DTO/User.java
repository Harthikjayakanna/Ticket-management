package com.project.ticketadministrations.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private long phone;
    private String password;
    private String type;
    @OneToMany(mappedBy = "openUser")
    @JsonIgnore
    private List<Tickets> opendTickets;
    @OneToMany(mappedBy = "closedUser")
    @JsonIgnore
    private List<Tickets> closedTickets;




}
