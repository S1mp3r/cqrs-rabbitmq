package br.rafael.users.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.rafael.users.api.entities.Customer;
import br.rafael.users.api.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final CustomerRepository repository;

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RuntimeException("Customer not found"));
    }

}