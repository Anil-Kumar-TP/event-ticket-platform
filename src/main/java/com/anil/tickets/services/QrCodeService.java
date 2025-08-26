package com.anil.tickets.services;

import com.anil.tickets.domain.entities.QrCode;
import com.anil.tickets.domain.entities.Ticket;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);
}
