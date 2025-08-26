package com.anil.tickets.services.impl;

import com.anil.tickets.domain.entities.Ticket;
import com.anil.tickets.domain.entities.TicketStatusEnum;
import com.anil.tickets.domain.entities.TicketType;
import com.anil.tickets.domain.entities.User;
import com.anil.tickets.exceptions.TicketSoldOutException;
import com.anil.tickets.exceptions.TicketTypeNotFoundException;
import com.anil.tickets.exceptions.UserNotFoundException;
import com.anil.tickets.repositories.TicketRepository;
import com.anil.tickets.repositories.TicketTypeRepository;
import com.anil.tickets.repositories.UserRepository;
import com.anil.tickets.services.QrCodeService;
import com.anil.tickets.services.TicketTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final QrCodeService qrCodeService;

    @Override
    @Transactional
    public Ticket purchaseTicket(UUID userId, UUID ticketTypeId) {

        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(String.format("User with ID  %s was not found", userId)));

        TicketType ticketType = ticketTypeRepository.findByIdWithLock(ticketTypeId).orElseThrow(()->new TicketTypeNotFoundException(String.format("Ticket Type with ID  %s was not found", ticketTypeId)));

        int purchasedTickets = ticketRepository.countByTicketTypeId(ticketType.getId());

        Integer totalAvailable = ticketType.getTotalAvailable();

        if (purchasedTickets + 1 > totalAvailable){
            throw new TicketSoldOutException();
        }

        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatusEnum.PURCHASED);
        ticket.setTicketType(ticketType);
        ticket.setPurchaser(user);

        Ticket savedTicket = ticketRepository.save(ticket);
        qrCodeService.generateQrCode(savedTicket);

        return ticketRepository.save(savedTicket);
    }
}
