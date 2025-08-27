package com.anil.tickets.mappers;

import com.anil.tickets.domain.dtos.GetTicketResponseDto;
import com.anil.tickets.domain.dtos.ListTicketResponseDto;
import com.anil.tickets.domain.dtos.ListTicketTicketTypeResponseDto;
import com.anil.tickets.domain.entities.Ticket;
import com.anil.tickets.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

    ListTicketTicketTypeResponseDto toListTicketTicketTypeResponseDto(TicketType ticketType);

    ListTicketResponseDto toListTicketResponseDto(Ticket ticket);

    @Mapping(target = "price",source = "ticketType.price")
    @Mapping(target = "description",source = "ticketType.description")
    @Mapping(target = "eventName",source = "ticketType.event.name")
    @Mapping(target = "eventVenue",source = "ticketType.event.venue")
    @Mapping(target = "eventStart",source = "ticketType.event.start")
    @Mapping(target = "eventEnd",source = "ticketType.event.end")
    GetTicketResponseDto toGetTicketResponseDto(Ticket ticket);
}
