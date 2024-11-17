package co.edu.udea.gestor_de_proyectos.model.dto;

import lombok.Data;

@Data
public class CambiarContrasenaDTO {
    private String username;
    private String newPassword;
    private String confirmPassword;
}