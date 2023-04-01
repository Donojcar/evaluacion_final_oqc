package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class MatriculaDTO {

    @EqualsAndHashCode.Include
    private Integer id;
    private LocalDateTime fechaMatricula;
    private boolean estado;
    @NotNull
    private EstudianteDTO estudiante;
    @NotNull
    @JsonManagedReference
    private List<MatriculaDetalleDTO> detalle;

}
