package br.rafael.users.api.models;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "procedures")
public class ProcedureDTO {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

}
