package br.rafael.users.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.rafael.users.api.entities.Customer;
import br.rafael.users.api.services.CustomerService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    
    private final CustomerService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> findAll() {
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Customer findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
