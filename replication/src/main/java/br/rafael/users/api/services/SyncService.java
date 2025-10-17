package br.rafael.users.api.services;

import org.springframework.stereotype.Service;

import br.rafael.users.api.models.AppointmentMongoDTO;
import br.rafael.users.api.models.CustomerDTO;
import br.rafael.users.api.models.ProcedureDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SyncService {
    
    private final CustomerService customerService;
    private final ProcedureService procedureService;
    private final AppointmentService appointmentService;

    public void syncCustomer(CustomerDTO customer) {
        customerService.save(customer);
        appointmentService.updateAppointmentCustomer(customer);
    }

    public void syncProcedure(ProcedureDTO procedure) {
        procedureService.save(procedure);
        appointmentService.updateAppointmentProcedure(procedure);
    }

    public void syncAppointment(AppointmentMongoDTO appointment) {
        appointmentService.save(appointment);
    }
}
