package br.rafael.users.api.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentDTO {
    
    private Long id;
    
    private LocalDateTime dateTime;
    private Boolean appointmentOpen;

    private Long customer;
    private Long procedure;

}
