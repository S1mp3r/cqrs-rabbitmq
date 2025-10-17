package br.rafael.users.api.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.rafael.users.api.models.AppointmentMongoDTO;
import br.rafael.users.api.models.CustomerDTO;
import br.rafael.users.api.models.ProcedureDTO;
import br.rafael.users.api.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {
 
    private final AppointmentRepository repository;

    @Transactional
    public void save(AppointmentMongoDTO appointment) {
        repository.save(appointment);
    }

    public void updateAppointmentCustomer(CustomerDTO customer) {
        
    }

    public void updateAppointmentProcedure(ProcedureDTO procedure) {
        
    }
}
