package br.rafael.users.api.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.rafael.users.api.entities.CustomerEntity;
import br.rafael.users.api.exceptions.NotFoundException;
import br.rafael.users.api.models.CustomerDTO;
import br.rafael.users.api.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final CustomerRepository repository;
    private final ModelMapper mapper;

    public CustomerDTO insert(CustomerDTO customerDTO) {
        final CustomerEntity customer = mapper.map(customerDTO, CustomerEntity.class);
        final CustomerEntity newCustomer = repository.save(customer);
        return mapper.map(newCustomer, CustomerDTO.class);
    }

    public CustomerDTO update(CustomerDTO customerDTO) {
        final CustomerEntity customer = repository.findById(customerDTO.getId()).orElseThrow(
                () -> new NotFoundException("Customer not found"));

        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());

        final CustomerEntity newCustomer = repository.save(customer);
        return mapper.map(newCustomer, CustomerDTO.class);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }
}
