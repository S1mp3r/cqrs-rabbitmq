package br.rafael.users.api.services;

import org.springframework.stereotype.Service;

import br.rafael.users.api.models.ProcedureDTO;
import br.rafael.users.api.repositories.ProcedureRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcedureService {
    
    private final ProcedureRepository repository;

    public void insertToReplicate(ProcedureDTO procedure) {
        repository.save(procedure);
    }

}
