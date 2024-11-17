package co.edu.udea.gestor_de_proyectos.service;

import co.edu.udea.gestor_de_proyectos.model.dto.ActualizarUsuarioDTO;
import co.edu.udea.gestor_de_proyectos.model.dto.CrearUsuarioDTO;
import co.edu.udea.gestor_de_proyectos.model.usuario.UsuarioModel;
import co.edu.udea.gestor_de_proyectos.model.dto.LoginUsuarioDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Tgl. Jhoan Villa.
 * Email: jhoan.villa
 * @version Id: <b>gestor-de-proyectos</b> 5/11/2024, 10:29 a. m.
 **/
public interface UsuarioService {

    UsuarioModel crearUsuario (CrearUsuarioDTO crearUsuarioDTO);

    Page<UsuarioModel> usuariosPaginados (int page, int size);

    UsuarioModel actualizarUsuario (String id, ActualizarUsuarioDTO actualizarUsuarioDTO);

    List<UsuarioModel> listarUsuarios ();
    
    UsuarioModel autenticarUsuario(LoginUsuarioDTO loginUsuarioDTO);
}
