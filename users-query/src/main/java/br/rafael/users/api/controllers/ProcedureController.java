package br.rafael.users.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.rafael.users.api.entities.Procedure;
import br.rafael.users.api.services.ProcedureService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("api/v1/procedure")
@RequiredArgsConstructor
public class ProcedureController {
    
    private final ProcedureService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Procedure> findAll() {
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Procedure findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
