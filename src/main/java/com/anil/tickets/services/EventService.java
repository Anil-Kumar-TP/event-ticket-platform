package com.anil.tickets.services;

import com.anil.tickets.domain.CreateEventRequest;
import com.anil.tickets.domain.entities.Event;

import java.util.UUID;

public interface EventService {

    Event createEvent(UUID organizerId,CreateEventRequest eventRequest);
}
