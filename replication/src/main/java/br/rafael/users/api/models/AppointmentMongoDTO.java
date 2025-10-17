package br.rafael.users.api.models;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "appointments")
public class AppointmentMongoDTO {
    
    private Long id;
    
    private LocalDateTime dateTime;
    private Boolean appointmentOpen;

    private CustomerDTO customer;
    private ProcedureDTO procedure;

}
