package br.rafael.users.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.rafael.users.api.models.ProcedureDTO;

@Repository
public interface ProcedureRepository extends MongoRepository<ProcedureDTO, Long> {
    
}
