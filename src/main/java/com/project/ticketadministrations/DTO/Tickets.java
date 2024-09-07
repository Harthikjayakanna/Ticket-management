package com.project.ticketadministrations.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String heading;
    private String explaination;
    private String closingStatement;
    private String status;
    @ManyToOne
    @JoinColumn
    private User closedUser;
    @ManyToOne
    @JoinColumn
    private User openUser;

}
