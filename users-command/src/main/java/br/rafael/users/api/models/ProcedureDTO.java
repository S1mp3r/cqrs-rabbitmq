package br.rafael.users.api.models;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcedureDTO {
    
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must have a maximum of 100 characters")
    private String name;

    @Size(max = 500, message = "Name must have a maximum of 500 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", message = "Price cannot be negative")
    @Digits(integer = 16, fraction = 2, message = "Price must have up to 16 integer digits and 2 decimals")
    private BigDecimal price;

}
