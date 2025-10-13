package br.rafael.users.api.entities;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_procedures")
@ToString(exclude = {"appointments"})
public class ProcedureEntity extends BaseEntity {
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must have a maximum of 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @Size(max = 500, message = "Name must have a maximum of 500 characters")
    @Column(length = 500)
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", message = "Price cannot be negative")
    @DecimalMax(value = "999999999999999999.99", message = "Price cannot be higher than 999.999.999.999.999.999,99")
    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

    @JsonIgnore
    @OneToMany(mappedBy = "procedure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<AppointmentEntity> appointments;

}
