package riwi.simulacro_SpringBoot.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riwi.simulacro_SpringBoot.api.dto.requests.SubmissionRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.SubmissionResponse;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.ISubmissionService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/submissions")
@AllArgsConstructor
public class SubmissionController {
    private final ISubmissionService submissionService;

    @Operation(summary = "mostrar todos los envíos",description = "Devuelve todos los envíos registrados")
    @GetMapping
   public ResponseEntity<Page<SubmissionResponse>> getAll(
           @RequestParam(defaultValue = "1")int page,
           @RequestParam(defaultValue = "5")int size
    ){
        return  ResponseEntity.ok(this.submissionService.getAll(page-1,size));
    }
    @Operation(summary = "Buscar envío por Id" ,description = "Ingrese el id del envío que desea buscar.")
    @GetMapping(path = "/{id}")
    public  ResponseEntity<SubmissionResponse> get(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.submissionService.get(id));
    }

    @Operation(summary = "Crear envío",description = "Ingrese los datos requeridos para el nuevo envío.")
    @PostMapping
    public ResponseEntity<SubmissionResponse> insert(
            @Validated @RequestBody SubmissionRequest request
            ){
        return ResponseEntity.ok(this.submissionService.create(request));
    }
    @Operation(summary = "Actualizar envío",description = "Ingrese la id del mensaje a modificar y los datos nuevos del envío")
    @PutMapping(path = "/{id}")
    public ResponseEntity<SubmissionResponse> update(
            @Validated @RequestBody SubmissionRequest request,
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.submissionService.update(request,id));
    }
    @Operation(summary = "Eliminar envío",description = "Ingrese la id del envío a eliminar")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String ,String >> delete(
            @PathVariable Long id
    ){
        Map<String,String > response = new HashMap<>();
        this.submissionService.delete(id);
        response.put("message","Envío eliminado correctamente");
        return  ResponseEntity.ok(response);
    }
}
