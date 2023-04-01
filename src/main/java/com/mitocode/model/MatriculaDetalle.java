/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class MatriculaDetalle {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String aula;
    @JoinColumn(name = "id_curso", nullable = false)
    @ManyToOne
    private Curso curso;
    @JoinColumn(name = "id_matricula", nullable = false)
    @ManyToOne
    private Matricula matricula;

}
