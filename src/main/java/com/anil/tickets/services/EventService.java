package com.anil.tickets.services;

import com.anil.tickets.domain.CreateEventRequest;
import com.anil.tickets.domain.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {

    Event createEvent(UUID organizerId,CreateEventRequest eventRequest);

    Page<Event> listEventsForOrganizer(UUID organizerId, Pageable pageable);
}
