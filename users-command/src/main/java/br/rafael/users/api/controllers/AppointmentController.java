package br.rafael.users.api.controllers;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.rafael.users.api.models.AppointmentDTO;
import br.rafael.users.api.services.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/appointments")
@Tag(name = "AppointmentController")
public class AppointmentController {
    
    private final AppointmentService service;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Create a new appointment into the database")
    public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO dto) {
        final URI uri = URI.create("api/v1/appointments");
        return ResponseEntity.created(uri).body(service.insert(dto));
    }

    @PatchMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Update the date time and/or if the appointment is open or not")
    public ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok().body(service.update(dto));
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Insert a customer and a procedure for an existing appointment")
    public ResponseEntity<AppointmentDTO> insertCustomer(@RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok().body(service.setCustomerToAppointment(dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(description = "Removes a appointment from the database")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }

}
