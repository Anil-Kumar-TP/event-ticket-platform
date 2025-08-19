package com.anil.tickets.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketTypeRequest {

    private String name;
    private String price;
    private String description;
    private Integer totalAvailable;
}
