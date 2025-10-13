package br.rafael.users.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class CustomerEntity extends BaseEntity {
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must have a maximum of 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Phone is required")
    @Size(max = 100, message = "Phone must have a maximum of 100 characters")
    @Column(nullable = false, length = 100)
    private String phone;

}
