package riwi.simulacro_SpringBoot.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riwi.simulacro_SpringBoot.api.dto.requests.LessonRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.LessonResponse;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.ILessonService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/lesson")
@AllArgsConstructor
public class LessonController {
    @Autowired
    private final ILessonService lessonService;

    @Operation(summary = "Traer todos las lesiones",description = "Trae todos las lesiones con su curso correspondiente")
    @GetMapping
    public ResponseEntity<Page<LessonResponse>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int size){
        return ResponseEntity.ok(this.lessonService.getAll(page-1,size));
    }

    @Operation(summary = "Buscar lesion por Id" ,description = "Ingrese el id de la lesion que desea buscar.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonResponse>getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.lessonService.get(id));

    }
    @Operation(summary = "Eliminar lesion",description = "Ingrese la id de la lesion a eliminar")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String >> delete(
            @PathVariable Long id
    ){
        Map<String ,String> response = new HashMap<>();
        this.lessonService.delete(id);
        response.put("message","Lesion eliminada correctamente");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Crear lesion",description = "Ingrese los datos requeridos para la nueva lesion.")
    @PostMapping
    public ResponseEntity<LessonResponse> insert (
            @Validated @RequestBody LessonRequest lessonRequest
    ){
        return ResponseEntity.ok(this.lessonService.create(lessonRequest));
    }

    @Operation(summary = "Actualizar lesion",description = "Ingrese la id de la lesion a modificar y los datos nuevos de la lesion")
    @PutMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody LessonRequest lessonRequest
            ){
        return ResponseEntity.ok(this.lessonService.update(lessonRequest,id));
    }



}
