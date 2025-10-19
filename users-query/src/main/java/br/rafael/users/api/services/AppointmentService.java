package br.rafael.users.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.rafael.users.api.entities.Appointment;
import br.rafael.users.api.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    
    private final AppointmentRepository repository;

    public List<Appointment> findAll() {
        return repository.findAll();
    }

    public Appointment findById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RuntimeException("Appointment not found"));
    }
}
