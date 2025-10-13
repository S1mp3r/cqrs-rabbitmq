package br.rafael.users.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.rafael.users.api.entities.ProcedureEntity;

@Repository
public interface ProcedureRepository extends JpaRepository<ProcedureEntity, Long> {}