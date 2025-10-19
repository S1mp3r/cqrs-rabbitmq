package br.rafael.users.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.rafael.users.api.entities.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {
    
}
