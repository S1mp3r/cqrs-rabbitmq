package br.rafael.users.api.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionBody {
    
    private Long timestamp;
    private Integer status;
    private List<String> messages;
    private String path;

}
