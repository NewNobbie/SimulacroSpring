package riwi.simulacro_SpringBoot.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import riwi.simulacro_SpringBoot.api.dto.requests.AssignmentRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.AssignmentResponse;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.IAssignmentService;

@RestController
@RequestMapping(path = "/assignment")
@AllArgsConstructor
public class AssignmentController {

    @Autowired
    private final IAssignmentService assignmentService;

    @Operation(summary = "Traer todas las tareas",description = "Trae todos las tareas con su curso correspondiente")
    @GetMapping
    public ResponseEntity<Page<AssignmentResponse>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int size){
        
        return ResponseEntity.ok(this.assignmentService.getAll(page -1, size));
    }

    @Operation(summary = "Buscar tarea  por Id" ,description = "Ingrese el id de la tarea que deseas buscar.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<AssignmentResponse>getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.assignmentService.get(id));

    }
    @Operation(summary = "Eliminar tarea",description = "Ingrese la id de la tarea a eliminar")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String >> delete(
            @PathVariable Long id
    ){
        Map<String ,String> response = new HashMap<>();
        this.assignmentService.delete(id);
        response.put("message","tarea eliminada correctamente");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Crear tarea",description = "Ingrese los datos requeridos para la nueva tarea.")
    @PostMapping
    public ResponseEntity<AssignmentResponse> insert (
        @Validated @RequestBody AssignmentRequest request
    ){
        return ResponseEntity.ok(this.assignmentService.create(request));

    }

    @Operation(summary = "Actualizar tarea",description = "Ingrese la id de la tarea a modificar y los datos nuevos de la tarea")
    @PutMapping(path = "/{id}")
    public ResponseEntity<AssignmentResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody AssignmentRequest assignmentRequest
            ){
        return ResponseEntity.ok(this.assignmentService.update(assignmentRequest,id));
    }
    
}
