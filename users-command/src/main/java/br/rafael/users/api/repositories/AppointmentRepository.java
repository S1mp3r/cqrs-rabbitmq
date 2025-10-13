package br.rafael.users.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.rafael.users.api.entities.AppointmentEntity;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {}
