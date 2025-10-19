package br.rafael.users.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.rafael.users.api.entities.Procedure;
import br.rafael.users.api.repositories.ProcedureRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcedureService {
    
    private final ProcedureRepository repository;

    public List<Procedure> findAll() {
        return repository.findAll();
    }

    public Procedure findById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RuntimeException("Procedure not found"));
    }
}