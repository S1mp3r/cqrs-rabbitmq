package br.rafael.users.api.controllers;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.rafael.users.api.models.ProcedureDTO;
import br.rafael.users.api.services.ProcedureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/procedures")
@RequiredArgsConstructor
@Tag(name = "ProcedureController")
public class ProcedureController {
    
    private final ProcedureService service;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Create a new procedure into the database")
    public ResponseEntity<ProcedureDTO> create(@Valid @RequestBody ProcedureDTO dto) {
        final URI uri = URI.create("api/v1/procedures");
        return ResponseEntity.created(uri).body(service.insert(dto));
    }

    @PatchMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Update the description and/or name and/or price from a procedure")
    public ResponseEntity<ProcedureDTO> update(@Valid @RequestBody ProcedureDTO dto) {
        return ResponseEntity.ok().body(service.update(dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(description = "Removes a procedure from the database")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }

}
