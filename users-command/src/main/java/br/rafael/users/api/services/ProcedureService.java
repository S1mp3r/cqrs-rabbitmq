package br.rafael.users.api.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.rafael.users.api.entities.ProcedureEntity;
import br.rafael.users.api.exceptions.NotFoundException;
import br.rafael.users.api.models.ProcedureDTO;
import br.rafael.users.api.repositories.ProcedureRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcedureService {
    
    private final BrokerService brokerService;
    private final ProcedureRepository repository;
    private final ModelMapper mapper;

    @Transactional
    public ProcedureDTO insert(ProcedureDTO procedureDTO) {
        final ProcedureEntity procedure = mapper.map(procedureDTO, ProcedureEntity.class);
        final ProcedureEntity newProcedure = repository.save(procedure);
        sendToQueue(newProcedure);
        return mapper.map(newProcedure, ProcedureDTO.class);
    }

    @Transactional
    public ProcedureDTO update(ProcedureDTO procedureDTO) {
        final ProcedureEntity procedure = repository.findById(procedureDTO.getId()).orElseThrow(
                () -> new NotFoundException("Customer not found"));

        procedure.setDescription(procedureDTO.getDescription());
        procedure.setName(procedureDTO.getName());
        procedure.setPrice(procedureDTO.getPrice());

        final ProcedureEntity newProcedure = repository.save(procedure);
        sendToQueue(newProcedure);
        return mapper.map(newProcedure, ProcedureDTO.class);
    }

    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
        // TODO: implement the delete logic
    }

    private void sendToQueue(ProcedureEntity procedure) {
        final ProcedureDTO procedureDTO = ProcedureDTO
            .builder()
            .id(procedure.getId())
            .name(procedure.getName())
            .description(procedure.getDescription())
            .price(procedure.getPrice())
            .build()
        ;
        brokerService.send("procedure", "proceduresExchange", procedureDTO);
    }
}
