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

    public void syncDeleteCustomer(Long customerId) {
        customerService.deleteById(customerId);
        appointmentService.updateAppointmentCustomerDeleted(customerId);
    }

    public void syncProcedure(ProcedureDTO procedure) {
        procedureService.save(procedure);
        appointmentService.updateAppointmentProcedure(procedure);
    }

    public void syncDeleteProcedure(Long procedureId) {
        procedureService.deleteById(procedureId);
        appointmentService.updateAppointmentProcedureDeleted(procedureId);
    }

    public void syncAppointment(AppointmentMongoDTO appointment) {
        appointmentService.save(appointment);
    }

    public void syncDeleteAppointment(Long appointmentId) {
        appointmentService.deleteById(appointmentId);
    }
}
