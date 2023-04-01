package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class MatriculaDetalleDTO {

    @EqualsAndHashCode.Include
    private Integer id;
    @JsonBackReference
    private MatriculaDTO matricula;
    private CursoDTO curso;
    private String aula;

}
