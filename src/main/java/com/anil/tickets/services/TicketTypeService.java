package com.anil.tickets.services;

import com.anil.tickets.domain.entities.Ticket;

import java.util.UUID;

public interface TicketTypeService {

    Ticket purchaseTicket(UUID userId,UUID ticketTypeId);
}
