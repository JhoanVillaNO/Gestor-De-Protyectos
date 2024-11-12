package co.edu.udea.gestor_de_proyectos.model.dto;

import lombok.Data;

/**
 * @author Tgl. Jhoan Villa.
 * Email: jhoan.villa
 * @version Id: <b>gestor-de-proyectos</b> 12/11/2024, 2:56 p. m.
 **/
@Data
public class ActualizarProyectoDTO {

    private String nombre;
    private String userId;
    private String categoria;
    private String estado;
}
