package co.edu.udea.gestor_de_proyectos.service;

import co.edu.udea.gestor_de_proyectos.model.categoria.CategoriaModel;

import java.util.List;

/**
 * @author Tgl. Jhoan Villa.
 * Email: jhoan.villa@dev-codes.io
 * @version Id: <b>gestor-de-proyectos</b> 18/11/2024, 8:48 a. m.
 **/
public interface CategoriaService {

    List<CategoriaModel> findAll();
}
