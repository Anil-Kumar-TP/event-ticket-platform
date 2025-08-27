package com.anil.tickets.mappers;

import com.anil.tickets.domain.dtos.ListTicketResponseDto;
import com.anil.tickets.domain.dtos.ListTicketTicketTypeResponseDto;
import com.anil.tickets.domain.entities.Ticket;
import com.anil.tickets.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

    ListTicketTicketTypeResponseDto toListTicketTicketTypeResponseDto(TicketType ticketType);

    ListTicketResponseDto toListTicketResponseDto(Ticket ticket);
}
