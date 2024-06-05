package riwi.simulacro_SpringBoot.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riwi.simulacro_SpringBoot.api.dto.requests.EnrollmentRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.EnrollmentResponse;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.IEnrollmentService;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping(path = "/enrollment")
@AllArgsConstructor
public class EnrollmentController {
    @Autowired
    private final IEnrollmentService enrollmentService;
    @Operation(summary = "Mostrar todos los enrollments", description = "Devuelve todos los enrollments paginados")
    @GetMapping
    public ResponseEntity<Page<EnrollmentResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return ResponseEntity.ok(this.enrollmentService.getAll(page-1,size));
    }

    @Operation(summary = "Buscar por id", description = "Devuelve el enrollment con el id proporcionado")
    @GetMapping(path = "/{id}")
    public ResponseEntity<EnrollmentResponse> get(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.enrollmentService.get(id));
    }

    @Operation(summary = "Crear un nuevo enrollment", description = "Ingrese los datos requeridos para crear un nuevo enrollment")
    @PostMapping
    public ResponseEntity<EnrollmentResponse> insert(
            @Validated @RequestBody EnrollmentRequest request
    ){
        return ResponseEntity.ok(this.enrollmentService.create(request));
    }

    @Operation(summary = "Actualizar un enrollment existente", description = "Ingrese el id del enrollment a actualizar y los nuevos datos")
    @PutMapping(path = "/{id}")
    public ResponseEntity<EnrollmentResponse> update(
            @Validated @RequestBody EnrollmentRequest request,
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.enrollmentService.update(request,id));
    }

    @Operation(summary = "Eliminar un enrollment", description = "Ingrese el id del enrollment a eliminar")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> delete(
            @PathVariable Long id
    )
    {
        Map<String ,String> response = new HashMap<>();
        this.enrollmentService.delete(id);
        response.put("enrollment","enrollment eliminado correctamente");
        return ResponseEntity.ok(response);
    }


}
