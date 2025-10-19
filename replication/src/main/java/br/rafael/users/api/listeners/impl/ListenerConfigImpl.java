package br.rafael.users.api.listeners.impl;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.rafael.users.api.listeners.ListenerConfig;
import br.rafael.users.api.models.AppointmentMongoDTO;
import br.rafael.users.api.models.CustomerDTO;
import br.rafael.users.api.models.ProcedureDTO;
import br.rafael.users.api.services.SyncService;
import br.rafael.users.api.utils.LogUtils;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableRabbit
@RequiredArgsConstructor
public class ListenerConfigImpl implements ListenerConfig {

    private final SyncService syncService;
    private final ObjectMapper objectMapper;

    @Override
    @RabbitListener(queues = "usersQueue")
    public void listenToUsersQueue(String message) {
        LogUtils.info("[INFO] Message received from queue customersQueue: ", message);
        try {
            final CustomerDTO customer = objectMapper.readValue(message, CustomerDTO.class);
            syncService.syncCustomer(customer);
            LogUtils.info("[INFO] Customer successfully saved");
        } catch (JsonProcessingException e) {
            LogUtils.error("[ERROR] An unexpected error has occourred ", e.getMessage());
        }
    }

    @Override
    @RabbitListener(queues = "proceduresQueue")
    public void listenToProcedureQueue(String message) {
        LogUtils.info("[INFO] Message received from queue proceduresQueue: ", message);
        try {
            final ProcedureDTO procedure = objectMapper.readValue(message, ProcedureDTO.class);
            syncService.syncProcedure(procedure);
            LogUtils.info("[INFO] Procedure successfully saved");
        } catch (JsonProcessingException e) {
            LogUtils.error("[ERROR] An unexpected error has occourred ", e.getMessage());
        }
    }

    @Override
    @RabbitListener(queues = "appointmentsQueue")
    public void listenToAppointmentQueue(String message) {
        LogUtils.info("[INFO] Message received from queue appointmentsQueue: ", message);
        try {
            final AppointmentMongoDTO appointment = objectMapper.readValue(message, AppointmentMongoDTO.class);
            syncService.syncAppointment(appointment);
            LogUtils.info("[INFO] Appointment successfully saved");
        } catch (JsonProcessingException e) {
            LogUtils.error("[ERROR] An unexpected error has occourred ", e.getMessage());
        }
    }

    @Override
    @RabbitListener(queues = "deletedUsersQueue")
    public void listenToUsersQueueDeleted(String message) {
        LogUtils.info("[INFO] Message received from queue deletedUsersQueue: ", message);
        try {
            final Long customerId = objectMapper.readValue(message, Long.class);
            syncService.syncDeleteCustomer(customerId);
            LogUtils.info("[INFO] Customer successfully deleted");
        } catch (JsonProcessingException e) {
            LogUtils.error("[ERROR] An unexpected error has occourred ", e.getMessage());
        }
    }

    @Override
    @RabbitListener(queues = "deletedProceduresQueue")
    public void listenToProcedureQueueDeleted(String message) {
        LogUtils.info("[INFO] Message received from queue deletedProceduresQueue: ", message);
        try {
            final Long procedureId = objectMapper.readValue(message, Long.class);
            syncService.syncDeleteProcedure(procedureId);
            LogUtils.info("[INFO] Procedure successfully deleted");
        } catch (JsonProcessingException e) {
            LogUtils.error("[ERROR] An unexpected error has occourred ", e.getMessage());
        }
    }

    @Override
    @RabbitListener(queues = "deletedAppointmentsQueue")
    public void listenToAppointmentQueueDeleted(String message) {
        LogUtils.info("[INFO] Message received from queue deletedAppointmentsQueue: ", message);
        try {
            final Long appointmentId = objectMapper.readValue(message, Long.class);
            syncService.syncDeleteAppointment(appointmentId);
            LogUtils.info("[INFO] Appointment successfully deleted");
        } catch (JsonProcessingException e) {
            LogUtils.error("[ERROR] An unexpected error has occourred ", e.getMessage());
        }
    }

    
}