package riwi.simulacro_SpringBoot.api.controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import riwi.simulacro_SpringBoot.api.dto.requests.CourseRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.CourseResponse;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.ICourseService;

// 17
@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {
    @Autowired
    private final ICourseService courseService;

    // 18 metodo getAll
    @Operation(summary = "Mostrar todos los cursos", description = "Trae todos los cursos registrados.")
    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int size
    ){
        return ResponseEntity.ok(this.courseService.getAll(page -1, size));
    }

    //19 metodo getById
    @Operation(summary = "Buscar curso por su ID",description = "Trae al curso que coincida con el id. \n\nParametro: \n- id: id del curso")
    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseResponse>getById(
        @PathVariable Long id
    ){
        return ResponseEntity.ok(this.courseService.get(id));
    }

    //20 delete
    @Operation(summary = "eliminar un curso", description = "Elimina un curso que coincida con su id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String>> delete(
        @PathVariable Long id){
            Map<String,String> response = new HashMap<>();
            response.put("message","curso eliminado correctamente");
            this.courseService.delete(id);
            return ResponseEntity.ok(response);
        }

    //22 insert
    @Operation(summary = "Crear Curso",description = "Ingrese los campos requeridos para crear un curso")
    @PostMapping
    public ResponseEntity<CourseResponse> insert(
        @Validated @RequestBody CourseRequest course){
            return ResponseEntity.ok(this.courseService.create(course));
        }

    //21 update
    @Operation(summary = "Actualizar curso",description = "Ingrese la id del curso a modificar y los datos nuevos del curso")
    @PutMapping(path = "/{id}")
    public ResponseEntity<CourseResponse> update(
        @PathVariable Long id,
        @Validated @RequestBody CourseRequest course){
        return ResponseEntity.ok(this.courseService.update(course, id));
    }
    
}
