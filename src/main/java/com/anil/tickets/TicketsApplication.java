package com.anil.tickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class TicketsApplication {

	public static void main(String[] args) {
		System.out.println("JVM Default TimeZone: " + TimeZone.getDefault().getID());
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		SpringApplication.run(TicketsApplication.class, args);
	}

}
