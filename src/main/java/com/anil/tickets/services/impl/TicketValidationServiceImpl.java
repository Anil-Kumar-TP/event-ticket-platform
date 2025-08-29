package com.anil.tickets.services.impl;

import com.anil.tickets.domain.entities.*;
import com.anil.tickets.exceptions.QrCodeNotFoundException;
import com.anil.tickets.exceptions.TicketNotFoundException;
import com.anil.tickets.repositories.QrCodeRepository;
import com.anil.tickets.repositories.TicketRepository;
import com.anil.tickets.repositories.TicketValidationRepository;
import com.anil.tickets.services.TicketValidationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketValidationServiceImpl implements TicketValidationService {

    private final QrCodeRepository qrCodeRepository;
    private final TicketValidationRepository ticketValidationRepository;
    private final TicketRepository ticketRepository;

    @Override
    public TicketValidation validateTicketByQrCode(UUID qrCodeId) {
        QrCode qrCode = qrCodeRepository.findByIdAndStatus(qrCodeId, QrCodeStatusEnum.ACTIVE)
                .orElseThrow(() -> new QrCodeNotFoundException(
                        String.format("QR code with id %s was not found", qrCodeId)));

        return validateTicket(qrCode.getTicket(), TicketValidationMethodEnum.QR_SCAN);
    }

    private TicketValidation validateTicket(Ticket ticket, TicketValidationMethodEnum method) {
        TicketValidation ticketValidation = new TicketValidation();
        ticketValidation.setTicket(ticket);
        ticketValidation.setValidationMethod(method);

        TicketValidationStatusEnum ticketValidationStatus = ticket.getTicketValidations()
                .stream()
                .filter(v -> TicketValidationStatusEnum.VALID.equals(v.getStatus()))
                .findFirst()
                .map(v -> TicketValidationStatusEnum.INVALID)
                .orElse(TicketValidationStatusEnum.VALID);

        ticketValidation.setStatus(ticketValidationStatus);

        return ticketValidationRepository.save(ticketValidation);
    }


    @Override
    public TicketValidation validateTicketManually(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(TicketNotFoundException::new);

        return validateTicket(ticket, TicketValidationMethodEnum.MANUAL);
    }
}
