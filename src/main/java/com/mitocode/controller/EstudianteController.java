package com.mitocode.controller;

import com.mitocode.dto.EstudianteDTO;
import com.mitocode.exception.NewModelNotFoundException;
import com.mitocode.model.Estudiante;
import com.mitocode.service.IEstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estudiante")
@RequiredArgsConstructor
public class EstudianteController {

    private final IEstudianteService service;

    @Qualifier("estudianteMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> readAll()throws Exception{
        List<EstudianteDTO> lst = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Estudiante obj = service.readById(id);
        if(obj==null){
            throw new NewModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> create(@Valid @RequestBody EstudianteDTO dto) throws Exception{
        Estudiante obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EstudianteDTO> update(@Valid @RequestBody EstudianteDTO dto) throws Exception{
        Estudiante obj = service.readById(dto.getId());

        if(obj == null){
            throw new NewModelNotFoundException("ID NOT FOUND: " + dto.getId());
        }

        Estudiante cat = service.update(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(cat), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Estudiante obj = service.readById(id);

        if(obj == null){
            throw new NewModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /* evaluacion */
    @GetMapping("/orden/edad/descendente")
    public ResponseEntity<List<EstudianteDTO>> readAllStudentsSortByAge()throws Exception{
        List<EstudianteDTO> lst = service.readAll().stream()
                .sorted( (x1,x2)-> x2.getEdad() - x1.getEdad() ) // Orden por edad de mayor a menor
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }
    /* evaluacion */


    private EstudianteDTO convertToDto(Estudiante obj) {
        return mapper.map(obj, EstudianteDTO.class);
    }

    private Estudiante convertToEntity(EstudianteDTO dto){
        return mapper.map(dto, Estudiante.class);
    }



}
