package br.rafael.users.api.services;

import java.util.Arrays;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.rafael.users.api.models.AppointmentMongoDTO;
import br.rafael.users.api.models.CustomerDTO;
import br.rafael.users.api.models.ProcedureDTO;
import br.rafael.users.api.repositories.AppointmentRepository;
import br.rafael.users.api.utils.LogUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {
 
    private final AppointmentRepository repository;
    private final MongoTemplate mongoTemplate;

    @Transactional
    public void save(AppointmentMongoDTO appointment) {
        try {
            LogUtils.info("Saving appointment: " + appointment.getId());
            repository.save(appointment);
        } catch (Exception e) {
            LogUtils.error("[ERROR] Error saving the appointment: " + e.getMessage());
            LogUtils.trace("[TRACE] " + Arrays.toString(e.getStackTrace()));
        }
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            LogUtils.info("Deleting appointment: " + id);
            repository.deleteById(id);
        } catch (Exception e) {
            LogUtils.error("[ERROR] Error deleting the appointment: " + e.getMessage());
            LogUtils.trace("[TRACE] " + Arrays.toString(e.getStackTrace()));
        }
    }

    public void updateAppointmentCustomer(CustomerDTO customer) {
        try {
            LogUtils.info("Updating appointment customer: " + customer.getId());
            Query query = new Query(Criteria.where("customer.id").is(customer.getId()));
            Update update = new Update().set("customer", customer);
            mongoTemplate.updateMulti(query, update, AppointmentMongoDTO.class);
        } catch (Exception e) {
            LogUtils.error("[ERROR] Error saving the appointment customer: " + e.getMessage());
            LogUtils.trace("[TRACE] " + Arrays.toString(e.getStackTrace()));
        }
    }

    public void updateAppointmentProcedure(ProcedureDTO procedure) {
        try {
            LogUtils.info("Updating appointment procedure: " + procedure.getId());
            Query query = new Query(Criteria.where("procedure.id").is(procedure.getId()));
            Update update = new Update()
                .set("procedure.name", procedure.getName())
                .set("procedure.description", procedure.getDescription());
            mongoTemplate.updateMulti(query, update, AppointmentMongoDTO.class);
        } catch (Exception e) {
            LogUtils.error("[ERROR] Error saving the appointment procedure: " + e.getMessage());
            LogUtils.trace("[TRACE] " + Arrays.toString(e.getStackTrace()));
        }
    }

    public void updateAppointmentCustomerDeleted(Long customerId) {
        try {
            LogUtils.info("Updating appointment customer deleted: " + customerId);
            Query query = new Query(Criteria.where("customer.id").is(customerId));
            Update update = new Update().set("customer", null);
            mongoTemplate.updateMulti(query, update, AppointmentMongoDTO.class);
        } catch (Exception e) {
            LogUtils.error("[ERROR] Error deleting the appointment customer: " + e.getMessage());
            LogUtils.trace("[TRACE] " + Arrays.toString(e.getStackTrace()));
        }
    }

    public void updateAppointmentProcedureDeleted(Long procedureId) {
        try {
            LogUtils.info("Updating appointment procedure deleted: " + procedureId);
            Query query = new Query(Criteria.where("procedure.id").is(procedureId));
            Update update = new Update().set("procedure", null);
            mongoTemplate.updateMulti(query, update, AppointmentMongoDTO.class);
        } catch (Exception e) {
            LogUtils.error("[ERROR] Error deleting the appointment procedure: " + e.getMessage());
            LogUtils.trace("[TRACE] " + Arrays.toString(e.getStackTrace()));
        }
    }
}
