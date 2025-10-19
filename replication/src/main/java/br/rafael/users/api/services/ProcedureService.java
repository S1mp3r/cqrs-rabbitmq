package br.rafael.users.api.services;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import br.rafael.users.api.models.ProcedureDTO;
import br.rafael.users.api.repositories.ProcedureRepository;
import br.rafael.users.api.utils.LogUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcedureService {
    
    private final ProcedureRepository repository;

    public void save(ProcedureDTO procedure) {
        try {
            LogUtils.info("Saving procedure: " + procedure.getId());
            repository.save(procedure);
        } catch (Exception e) {
            LogUtils.error("[ERROR] Error saving the procedure: " + e.getMessage());
            LogUtils.trace("[TRACE] " + Arrays.toString(e.getStackTrace()));
        }
    }

    public void deleteById(Long id) {
        try {
            LogUtils.info("Deleting procedure: " + id);
            repository.deleteById(id);
        } catch (Exception e) {
            LogUtils.error("[ERROR] Error deleting the procedure: " + e.getMessage());
            LogUtils.trace("[TRACE] " + Arrays.toString(e.getStackTrace()));
        }
    }

}
