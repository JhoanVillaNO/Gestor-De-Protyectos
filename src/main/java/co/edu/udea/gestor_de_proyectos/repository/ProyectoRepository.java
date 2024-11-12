package co.edu.udea.gestor_de_proyectos.repository;

import co.edu.udea.gestor_de_proyectos.entity.Proyecto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tgl. Jhoan Villa.
 * Email: jhoan.villa
 * @version Id: <b>gestor-de-proyectos</b> 5/11/2024, 10:21 a. m.
 **/
@Repository
public interface ProyectoRepository extends MongoRepository<Proyecto, String> {
}
