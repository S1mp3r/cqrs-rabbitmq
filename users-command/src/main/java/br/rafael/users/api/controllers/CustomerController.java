package br.rafael.users.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.rafael.users.api.models.CustomerDTO;
import br.rafael.users.api.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
@Tag(name = "CustomerController")
public class CustomerController {
    
    private final CustomerService service;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Create a new customer into the database")
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO dto) {
        final URI uri = URI.create("api/v1/customers");
        return ResponseEntity.created(uri).body(service.insert(dto));
    }

    @PatchMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Update the email and/or phone number from a customer")
    public ResponseEntity<CustomerDTO> update(@Valid @RequestBody CustomerDTO dto) {
        return ResponseEntity.ok().body(service.update(dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(description = "Removes a customer from the database")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }

}
