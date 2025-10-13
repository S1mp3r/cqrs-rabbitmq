package br.rafael.users.api.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.rafael.users.api.entities.AppointmentEntity;
import br.rafael.users.api.entities.CustomerEntity;
import br.rafael.users.api.entities.ProcedureEntity;
import br.rafael.users.api.exceptions.NotFoundException;
import br.rafael.users.api.models.AppointmentDTO;
import br.rafael.users.api.repositories.AppointmentRepository;
import br.rafael.users.api.repositories.CustomerRepository;
import br.rafael.users.api.repositories.ProcedureRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    
    private final AppointmentRepository repository;
    private final CustomerRepository customerRepository;
    private final ProcedureRepository procedureRepository;
    private final ModelMapper mapper;

    @Transactional
    public AppointmentDTO insert(AppointmentDTO appointmentDTO) {
        final AppointmentEntity appointment = mapper.map(appointmentDTO, AppointmentEntity.class);
        appointment.setAppointmentOpen(true);
        final AppointmentEntity newAppointmentDTO = repository.save(appointment);

        return mapper.map(newAppointmentDTO, AppointmentDTO.class);
    }

    @Transactional
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        final AppointmentEntity appointment = repository.findById(appointmentDTO.getId()).orElseThrow(
                () -> new NotFoundException("Appointment not found")
            );

        appointment.setAppointmentOpen(appointmentDTO.getAppointmentOpen());
        appointment.setDateTime(appointmentDTO.getDateTime());

        final AppointmentEntity newAppointmentDTO = repository.save(appointment);
        return buildAppointment(newAppointmentDTO);
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
            .dateTime(appointment.getDateTime())
            .customer(appointment.getCustomer().getId())
            .procedure(appointment.getProcedure().getId())
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
}
