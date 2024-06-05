package riwi.simulacro_SpringBoot.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riwi.simulacro_SpringBoot.api.dto.requests.MessageRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.MessageResponse;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.IMessageService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/message")
@AllArgsConstructor
public class MessageController {
    @Autowired
    private final IMessageService messageService;

    @Operation(summary = "mostrar todos los mensajes",description = "Devuelve todos los mensajes registrados")
    @GetMapping
    public ResponseEntity<Page<MessageResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return ResponseEntity.ok(this.messageService.getAll(page-1,size));
    }

    @Operation(summary = "Buscar mensaje por Id" ,description = "Ingrese el id del mensaje que desea buscar.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<MessageResponse> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.messageService.get(id));
    }

    @Operation(summary = "Eliminar mensaje",description = "Ingrese la id del mensaje a eliminar")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String >> delete(
            @PathVariable Long id
    ){
        Map<String ,String> response = new HashMap<>();
        this.messageService.delete(id);
        response.put("message","mensaje eliminado correctamente");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Crear mensaje",description = "Ingrese los datos requeridos para el nuevo mensaje.")
    @PostMapping
    public ResponseEntity<MessageResponse> insert(
            @Validated @RequestBody MessageRequest messageRequest
            ){
        return ResponseEntity.ok(this.messageService.create(messageRequest));
    }

    @Operation(summary = "Actualizar mensaje",description = "Ingrese la id del mensaje a modificar y los datos nuevos del mensaje")
    @PutMapping(path = "/{id}")
    public ResponseEntity<MessageResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody MessageRequest messageRequest
    ){
        return ResponseEntity.ok(this.messageService.update(messageRequest,id));
    }

}
