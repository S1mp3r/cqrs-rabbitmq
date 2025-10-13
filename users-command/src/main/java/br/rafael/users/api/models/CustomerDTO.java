package br.rafael.users.api.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must have a maximum of 100 characters")
    private String name;

    @NotBlank(message = "Phone is required")
    @Size(max = 100, message = "Phone must have a maximum of 100 characters")
    private String phone;

    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email must have a maximum of 255 characters")
    private String email;

}
