package cl.edubio360.auth.controller;

import cl.edubio360.auth.dto.UserRequest;
import cl.edubio360.auth.dto.UserResponse;
import cl.edubio360.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserResponse> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public UserResponse obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse crear(@Valid @RequestBody UserRequest request) { return service.crear(request); }

    @PutMapping("/{id}")
    public UserResponse actualizar(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
