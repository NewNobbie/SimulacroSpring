package riwi.simulacro_SpringBoot.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface ControllerGeneric<E, R, ID> {
    @Operation(summary = "Mostrar todos los registros", description = "Devuelve todos los registros paginados")
    @GetMapping
    public ResponseEntity<Page<R>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    );

    @Operation(summary = "Buscar por ID", description = "Devuelve el registro con el ID proporcionado")
    @GetMapping(path = "/{id}")
    public ResponseEntity<R> get(
            @PathVariable ID id
    );

    @Operation(summary = "Crear un nuevo registro", description = "Ingrese los datos requeridos para crear un nuevo registro")
    @PostMapping
    public ResponseEntity<R> insert(
            @Validated @RequestBody E request
    );

    @Operation(summary = "Actualizar un registro existente", description = "Ingrese el ID del registro a actualizar y los nuevos datos")
    @PutMapping (path = "/{id}")
    public ResponseEntity<R> update(
            @Validated @RequestBody E request,
            @PathVariable ID id
    );

    @Operation(summary = "Eliminar un registro", description = "Ingrese el ID del registro a eliminar")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> delete(
            @PathVariable ID id
    );
}
