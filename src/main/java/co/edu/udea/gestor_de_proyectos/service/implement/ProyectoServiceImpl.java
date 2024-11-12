package co.edu.udea.gestor_de_proyectos.service.implement;

import co.edu.udea.gestor_de_proyectos.entity.Proyecto;
import co.edu.udea.gestor_de_proyectos.model.dto.ActualizarProyectoDTO;
import co.edu.udea.gestor_de_proyectos.model.dto.CrearProyectoDTO;
import co.edu.udea.gestor_de_proyectos.model.proyecto.ProyectoModel;
import co.edu.udea.gestor_de_proyectos.repository.ProyectoRepository;
import co.edu.udea.gestor_de_proyectos.service.FechaActualService;
import co.edu.udea.gestor_de_proyectos.service.ProyectoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final FechaActualService fechaActual;

    @Override
    public ProyectoModel crearProyecto(CrearProyectoDTO crearProyectoDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(generateId(null));
        proyecto.setNombre(crearProyectoDTO.getNombre());
        proyecto.setUserId(crearProyectoDTO.getUserId());
        proyecto.setCategoria(crearProyectoDTO.getCategoria());
        proyecto.setFechaCreacion(fechaActual.getCurrentDate());
        proyecto.setFechaModificacion(fechaActual.getCurrentDate());
        proyecto.setEstado(crearProyectoDTO.getEstado());

        Proyecto savedProyecto = proyectoRepository.save(proyecto);
        return mapToModel(savedProyecto);
    }

    @Override
    public Page<ProyectoModel> proyectosPaginados(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Proyecto> proyectosPage = proyectoRepository.findAll(pageable);
        return proyectosPage.map(this::mapToModel);
    }

    @Override
    public ProyectoModel actualizarProyecto(String id,  ActualizarProyectoDTO actualizarProyectoDTO) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));
        proyecto.setNombre(actualizarProyectoDTO.getNombre());
        proyecto.setCategoria(actualizarProyectoDTO.getCategoria());
        proyecto.setFechaModificacion(fechaActual.getCurrentDate());
        proyecto.setEstado(actualizarProyectoDTO.getEstado());

        Proyecto updatedProyecto = proyectoRepository.save(proyecto);
        return mapToModel(updatedProyecto);
    }

    @Override
    public List<ProyectoModel> listarProyectos() {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        return proyectos.stream().map(this::mapToModel).toList();
    }

    private ProyectoModel mapToModel(Proyecto proyecto) {
        ProyectoModel model = new ProyectoModel();
        model.setId(proyecto.getId());
        model.setNombre(proyecto.getNombre());
        model.setUserId(proyecto.getUserId());
        model.setCategoria(proyecto.getCategoria());
        model.setFechaCreacion(proyecto.getFechaCreacion());
        model.setFechaModificacion(proyecto.getFechaModificacion());
        model.setEstado(proyecto.getEstado());
        return model;
    }

    private String generateId(String id) {
        return (id == null) ? UUID.randomUUID().toString() : id;
    }
}

