package br.rafael.users.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.rafael.users.api.models.CustomerDTO;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDTO, Long> {
    
}
