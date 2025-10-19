package br.rafael.users.api.services;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import br.rafael.users.api.models.CustomerDTO;
import br.rafael.users.api.repositories.CustomerRepository;
import br.rafael.users.api.utils.LogUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public void save(CustomerDTO customer) {
        try {
            LogUtils.info("Saving customer: " + customer.getId());
            repository.save(customer);
        } catch (Exception e) {
            LogUtils.error("[ERROR] Error saving the customer: " + e.getMessage());
            LogUtils.trace("[TRACE] " + Arrays.toString(e.getStackTrace()));
        }
    }

    public void deleteById(Long id) {
        try {
            LogUtils.info("Deleting customer: " + id);
            repository.deleteById(id);
        } catch (Exception e) {
            LogUtils.error("[ERROR] Error deleting the customer: " + e.getMessage());
            LogUtils.trace("[TRACE] " + Arrays.toString(e.getStackTrace()));
        }
    }
}
