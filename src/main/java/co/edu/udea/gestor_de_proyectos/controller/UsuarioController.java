package co.edu.udea.gestor_de_proyectos.controller;

import co.edu.udea.gestor_de_proyectos.model.dto.LoginUsuarioDTO;
import co.edu.udea.gestor_de_proyectos.model.dto.ActualizarUsuarioDTO;
import co.edu.udea.gestor_de_proyectos.model.dto.CrearUsuarioDTO;
import co.edu.udea.gestor_de_proyectos.model.usuario.UsuarioModel;
import co.edu.udea.gestor_de_proyectos.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tgl. Jhoan Villa.
 * Email: jhoan.villa
 * @version Id: <b>gestor-de-proyectos</b> 5/11/2024, 10:33 a. m.
 **/
@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*") // Permitir solicitudes de cualquier origen
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<UsuarioModel> crearUsuario(@RequestBody CrearUsuarioDTO crearUsuarioDTO) {
        UsuarioModel usuarioModel = usuarioService.crearUsuario(crearUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioModel);
    }

    @GetMapping("/pagina/{page}/{size}")
    public ResponseEntity<Page<UsuarioModel>> listarUsuariosPaginados(@PathVariable int page, @PathVariable int size) {
        Page<UsuarioModel> usuarios = usuarioService.usuariosPaginados(page, size);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        List<UsuarioModel> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<UsuarioModel> actualizarUsuario(@PathVariable String id,
                                                          @RequestBody ActualizarUsuarioDTO actualizarUsuarioDTO) {
        UsuarioModel usuarioModel = usuarioService.actualizarUsuario(id, actualizarUsuarioDTO);
        return ResponseEntity.ok(usuarioModel);
    }
    
    @PostMapping("/login")
    public ResponseEntity<UsuarioModel> login(@RequestBody LoginUsuarioDTO loginUsuarioDTO) {
        UsuarioModel usuario = usuarioService.autenticarUsuario(loginUsuarioDTO);
        return ResponseEntity.ok(usuario);
    }
}
