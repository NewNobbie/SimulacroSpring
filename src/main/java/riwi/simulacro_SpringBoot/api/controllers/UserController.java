package riwi.simulacro_SpringBoot.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riwi.simulacro_SpringBoot.api.dto.requests.UserRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.UserResponse;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.IUserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final IUserService userService;

    @Operation(summary = "Mostrar todos los usuarios", description = "Trae todos los usuarios registrados.")
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return  ResponseEntity.ok(this.userService.getAll(page -1, size));
    }

    @Operation(summary = "Buscar usuario por su ID",description = "Trae al usuario que coincida con la id. \n\nParametro: \n- id: id del usuario ")
    @GetMapping(path = "/{id}")
    public  ResponseEntity<UserResponse> getById(
            @PathVariable Long id
    ){
        return  ResponseEntity.ok(this.userService.get(id));
    }
    @Operation(summary = "Crear un usuario",description = "Permite crear un usuario rellenando los campos requeridos.")
    @PostMapping
    public ResponseEntity<UserResponse> insert(
            @Validated @RequestBody UserRequest user ){
        return  ResponseEntity.ok(this.userService.create(user));
    }
    @Operation(summary = "Eliminar Usuario",description = "Elimina un usuario proporcionando su id ")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String>> delete(
            @PathVariable Long id){
        Map<String ,String > response = new HashMap<>();
        response.put("message","Usuario Eliminado correctamente.");
        this.userService.delete(id);
        return  ResponseEntity.ok(response);
    }
    @Operation(summary = "Actualizar usuario",description = "Permite actualizar un usuario rellenando los campos con los datos nuevos y proporcionado su id.")
    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody UserRequest user){
        return  ResponseEntity.ok(this.userService.update(user,id));
    }



}
