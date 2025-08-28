package com.anil.tickets.domain.dtos;

import com.anil.tickets.domain.entities.TicketValidationMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketValidationRequestDto {

    private UUID id;//either qr or manual
    private TicketValidationMethodEnum method;
}
