package com.anil.tickets.services;

import com.anil.tickets.domain.entities.QrCode;
import com.anil.tickets.domain.entities.Ticket;

import java.util.UUID;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);

    byte[] getQrCodeImageForUserAndTicket(UUID userId,UUID ticketId);
}
