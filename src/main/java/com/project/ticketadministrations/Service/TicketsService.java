package com.project.ticketadministrations.Service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ticketadministrations.DAO.TicketDao;
import com.project.ticketadministrations.DAO.UserDao;
import com.project.ticketadministrations.DTO.MailConfig;
import com.project.ticketadministrations.DTO.ResponseStructure;
import com.project.ticketadministrations.DTO.Tickets;
import com.project.ticketadministrations.DTO.User;
import com.project.ticketadministrations.Exception.IDnotFoundException;

@Service
public class TicketsService {
    @Autowired
    private UserDao udao;
    @Autowired
    private TicketDao tDao;
    @Autowired
    private EmailService emailService;
    @Autowired
    private MailConfig mailConfig;
    ResponseStructure<Tickets> resTicket = new ResponseStructure<>();

    // Saving the tickets
    public ResponseEntity<ResponseStructure<Tickets>> risingTicket(int userId, Tickets t) {
        Optional<User> resU = udao.findById(userId);
        if (resU.isPresent()) {
            User u = resU.get();
            u.getOpendTickets().add(t);
            t.setOpenUser(u);
            udao.updateUser(u);
            t.setStatus("open");
            tDao.saveTickets(t);
            resTicket.setData(t);
            resTicket.setMessage("Ticket has been rasied");
            resTicket.setStatusCode(HttpStatus.CREATED.value());
            return new ResponseEntity<ResponseStructure<Tickets>>(resTicket, HttpStatus.CREATED);
        }
        throw new IDnotFoundException();
    }

    // Updating the tickets
    public ResponseEntity<ResponseStructure<Tickets>> closingTheTicket(int userId, Tickets t) {
        Optional<User> resU = udao.findById(userId);
        Optional<Tickets> resT = tDao.findById(t.getId());
        if (resU.isPresent() && resT.isPresent()) {
            User u = resU.get();
            Tickets tic = resT.get();
            u.getClosedTickets().add(tic);
            tic.setClosedUser(u);
            tic.setStatus("closed");
            tic.setClosingStatement(t.getClosingStatement());
            udao.updateUser(u);
            tDao.saveTickets(tic);
            User openUser = tic.getOpenUser();
            mailConfig.setTo(openUser.getEmail());
            mailConfig.setSubject("Ticket closed");
            mailConfig.setText(" Dear " + u.getName() + "\n" +
                    "Ticktet you have raised for " + tic.getHeading()
                    + " has been resolved , check our website for more information");
            emailService.sendMail(mailConfig);
            resTicket.setData(tic);
            resTicket.setMessage("Ticket has been closed");
            resTicket.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<ResponseStructure<Tickets>>(resTicket, HttpStatus.OK);
        }
        throw new IDnotFoundException();
    }

    // Get all the Tickets
    public ResponseEntity<ResponseStructure<List<Tickets>>> allTickets() {
        ResponseStructure<List<Tickets>> resAT = new ResponseStructure<>();
        List<Tickets> allTickets = tDao.findAll();
        resAT.setData(allTickets);
        resAT.setMessage("All the Tickets so far");
        resAT.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ResponseStructure<List<Tickets>>>(resAT, HttpStatus.OK);
    }

    // Get the openTickets
    public ResponseEntity<ResponseStructure<List<Tickets>>> allOpenTickets(int id) {
        ResponseStructure<List<Tickets>> resAT = new ResponseStructure<>();
        List<Tickets> allTickets = tDao.findByOpenedUser(id);
        resAT.setData(allTickets);
        resAT.setMessage("All the Tickets so far by openers");
        resAT.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ResponseStructure<List<Tickets>>>(resAT, HttpStatus.OK);
    }

    // Get the closedTickets
    public ResponseEntity<ResponseStructure<List<Tickets>>> allCloseTickets(int id) {
        ResponseStructure<List<Tickets>> resAT = new ResponseStructure<>();
        List<Tickets> allTickets = tDao.findbyClosedUser(id);
        resAT.setData(allTickets);
        resAT.setMessage("All the Tickets so far by  ");
        resAT.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ResponseStructure<List<Tickets>>>(resAT, HttpStatus.OK);
    }
}
