package br.rafael.users.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.rafael.users.api.models.AppointmentMongoDTO;

@Repository
public interface AppointmentRepository extends MongoRepository<AppointmentMongoDTO, Long> {
    
}
