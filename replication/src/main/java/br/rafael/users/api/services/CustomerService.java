package br.rafael.users.api.services;

import org.springframework.stereotype.Service;

import br.rafael.users.api.models.CustomerDTO;
import br.rafael.users.api.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public void insertToReplicate(CustomerDTO customer) {
        repository.save(customer);
    }

}
