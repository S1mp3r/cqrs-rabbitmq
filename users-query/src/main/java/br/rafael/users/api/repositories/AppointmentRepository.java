package br.rafael.users.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.rafael.users.api.entities.Appointment;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, Long> {
    
}
