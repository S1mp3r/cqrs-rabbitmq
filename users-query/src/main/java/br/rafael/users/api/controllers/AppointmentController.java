package br.rafael.users.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.rafael.users.api.entities.Appointment;
import br.rafael.users.api.services.AppointmentService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    
    private final AppointmentService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Appointment> findAll() {
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Appointment findById(@PathVariable Long id) {
        return service.findById(id);
    }
}