package co.edu.udea.gestor_de_proyectos.service.implement;


import co.edu.udea.gestor_de_proyectos.entity.Usuario;
import co.edu.udea.gestor_de_proyectos.model.dto.ActualizarUsuarioDTO;
import co.edu.udea.gestor_de_proyectos.model.dto.CrearUsuarioDTO;
import co.edu.udea.gestor_de_proyectos.model.usuario.UsuarioModel;
import co.edu.udea.gestor_de_proyectos.repository.UsuarioRepository;
import co.edu.udea.gestor_de_proyectos.service.FechaActualService;
import co.edu.udea.gestor_de_proyectos.service.UsuarioService;
import co.edu.udea.gestor_de_proyectos.model.dto.LoginUsuarioDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.UUID;

/**
 * @author Tgl. Jhoan Villa.
 * Email: jhoan.villa
 * @version Id: <b>gestor-de-proyectos</b> 5/11/2024, 10:30 a. m.
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final FechaActualService fechaActual;

    @Override
    public UsuarioModel autenticarUsuario(LoginUsuarioDTO loginUsuarioDTO) {
    	Usuario usuario = usuarioRepository
                .findByUserAndPassword(loginUsuarioDTO.getUser(), loginUsuarioDTO.getPassword())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o contraseña incorrectos"));
                //.orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos"));
    	return mapToModel(usuario);
    }
    
    @Override
    public UsuarioModel crearUsuario(CrearUsuarioDTO crearUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(generateId(null));
        usuario.setNombre(crearUsuarioDTO.getNombre());
        usuario.setApellidos(crearUsuarioDTO.getApellidos());
        usuario.setEdad(crearUsuarioDTO.getEdad());
        usuario.setEstrato(crearUsuarioDTO.getEstrato());
        usuario.setFechaCreacion(fechaActual.getCurrentDate());
        usuario.setFechaModificacion(fechaActual.getCurrentDate());
        usuario.setCiudad(crearUsuarioDTO.getCiudad());
        usuario.setUser(crearUsuarioDTO.getUser());
        usuario.setPassword(crearUsuarioDTO.getPassword());
        usuario.setRol(crearUsuarioDTO.getRol());

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return mapToModel(savedUsuario);
    }

    @Override
    public Page<UsuarioModel> usuariosPaginados(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Usuario> usuariosPage = usuarioRepository.findAll(pageable);
        return usuariosPage.map(this::mapToModel);
    }

    @Override
    public UsuarioModel actualizarUsuario(String id, ActualizarUsuarioDTO actualizarUsuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        usuario.setNombre(actualizarUsuarioDTO.getNombre());
        usuario.setApellidos(actualizarUsuarioDTO.getApellidos());
        usuario.setEdad(actualizarUsuarioDTO.getEdad());
        usuario.setEstrato(actualizarUsuarioDTO.getEstrato());
        usuario.setCiudad(actualizarUsuarioDTO.getCiudad());
        usuario.setFechaModificacion(fechaActual.getCurrentDate());
        usuario.setRol(actualizarUsuarioDTO.getRol());

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return mapToModel(updatedUsuario);
    }

    @Override
    public List<UsuarioModel> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(this::mapToModel).toList();
    }

    private UsuarioModel mapToModel(Usuario usuario) {
        UsuarioModel model = new UsuarioModel();
        model.setId(usuario.getId());
        model.setNombre(usuario.getNombre());
        model.setApellidos(usuario.getApellidos());
        model.setEdad(usuario.getEdad());
        model.setEstrato(usuario.getEstrato());
        model.setFechaCreacion(usuario.getFechaCreacion());
        model.setFechaModificacion(usuario.getFechaModificacion());
        model.setCiudad(usuario.getCiudad());
        return model;
    }

    private String generateId(String id) {
        return (id == null) ? UUID.randomUUID().toString() : id;
    }
}

