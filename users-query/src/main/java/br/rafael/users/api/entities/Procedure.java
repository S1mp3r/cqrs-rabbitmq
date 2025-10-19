package br.rafael.users.api.entities;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "procedures")
public class Procedure {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

}
