package br.rafael.users.api.entities;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "appointments")
public class Appointment {
    
    private Long id;
    
    private LocalDateTime dateTime;
    private Boolean appointmentOpen;

    private Customer customer;
    private Procedure procedure;

}
