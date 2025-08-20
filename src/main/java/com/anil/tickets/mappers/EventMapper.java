package com.anil.tickets.mappers;

import com.anil.tickets.domain.CreateEventRequest;
import com.anil.tickets.domain.CreateTicketTypeRequest;
import com.anil.tickets.domain.dtos.CreateEventRequestDto;
import com.anil.tickets.domain.dtos.CreateEventResponseDto;
import com.anil.tickets.domain.dtos.CreateTicketTypeRequestDto;
import com.anil.tickets.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);
}
