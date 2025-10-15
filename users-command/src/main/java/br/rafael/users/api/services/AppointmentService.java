package br.rafael.users.api.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.rafael.users.api.entities.AppointmentEntity;
import br.rafael.users.api.entities.CustomerEntity;
import br.rafael.users.api.entities.ProcedureEntity;
import br.rafael.users.api.exceptions.NotFoundException;
import br.rafael.users.api.models.AppointmentDTO;
import br.rafael.users.api.models.AppointmentMongoDTO;
import br.rafael.users.api.models.CustomerDTO;
import br.rafael.users.api.models.ProcedureDTO;
import br.rafael.users.api.repositories.AppointmentRepository;
import br.rafael.users.api.repositories.CustomerRepository;
import br.rafael.users.api.repositories.ProcedureRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    
    private final BrokerService brokerService;
    private final AppointmentRepository repository;
    private final CustomerRepository customerRepository;
    private final ProcedureRepository procedureRepository;
    private final ModelMapper mapper;

    @Transactional
    public AppointmentDTO insert(AppointmentDTO appointmentDTO) {
        final AppointmentEntity appointment = mapper.map(appointmentDTO, AppointmentEntity.class);
        appointment.setAppointmentOpen(true);
        final AppointmentEntity newAppointment = repository.save(appointment);
        sendToQueue(newAppointment);
        return mapper.map(newAppointment, AppointmentDTO.class);
    }

    @Transactional
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        final AppointmentEntity appointment = repository.findById(appointmentDTO.getId()).orElseThrow(
                () -> new NotFoundException("Appointment not found")
            );

        appointment.setAppointmentOpen(appointmentDTO.getAppointmentOpen());
        if (appointmentDTO.getDateTime() != null) {
            appointment.setDateTime(appointmentDTO.getDateTime());
        }

        final AppointmentEntity newAppointment = repository.save(appointment);
        sendToQueue(newAppointment);
        return buildAppointment(newAppointment);
    }

    @Transactional
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentDTO) {
        final AppointmentEntity appointment = repository.findById(appointmentDTO.getId()).orElseThrow(
                () -> new NotFoundException("Appointment not found")
            );
        final CustomerEntity customer = findCustomerById(appointmentDTO.getCustomer());
        final ProcedureEntity procedure = findProcedureById(appointmentDTO.getProcedure());

        appointment.setCustomer(customer);
        appointment.setProcedure(procedure);
        appointment.setAppointmentOpen(false);
        
        final AppointmentEntity newAppointmentDTO = repository.save(appointment);
        sendToQueue(appointment);
        return buildAppointment(newAppointmentDTO);
    }

    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }

    private AppointmentDTO buildAppointment(AppointmentEntity appointment) {
        return AppointmentDTO
            .builder()
            .id(appointment.getId())
            .appointmentOpen(appointment.getAppointmentOpen())
            .customer(appointment.getCustomer().getId())
            .procedure(appointment.getProcedure().getId())
            .dateTime(appointment.getDateTime())
            .build();
    }

    private CustomerEntity findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Customer not found")
        );
    }

    private ProcedureEntity findProcedureById(Long id) {
        return procedureRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Procedure not found")
        );
    }

    private void sendToQueue(AppointmentEntity appointment) {
        final AppointmentMongoDTO appointmentDTO = AppointmentMongoDTO
            .builder()
            .id(appointment.getId())
            .appointmentOpen(appointment.getAppointmentOpen())
            .dateTime(appointment.getDateTime())
            .customer(buildCustomer(appointment))
            .procedure(buildProcedure(appointment))
            .build();
        brokerService.send("appointment", "appointmentsExchange", appointmentDTO);
    }

    private CustomerDTO buildCustomer(AppointmentEntity appointment) {
        var customer = appointment.getCustomer() == null ? null : appointment.getCustomer();
        if (customer == null) return null;
        return CustomerDTO
            .builder()
            .id(customer.getId())
            .name(customer.getName())
            .email(customer.getEmail())
            .phone(customer.getPhone())
            .build()
        ;
    }

    private ProcedureDTO buildProcedure(AppointmentEntity appointment) {
        var procedure = appointment.getProcedure() == null ? null : appointment.getProcedure();
        if (procedure == null) return null;
        return ProcedureDTO
            .builder()
            .id(procedure.getId())
            .name(procedure.getName())
            .description(procedure.getDescription())
            .price(procedure.getPrice())
            .build()
        ;
    }
}
