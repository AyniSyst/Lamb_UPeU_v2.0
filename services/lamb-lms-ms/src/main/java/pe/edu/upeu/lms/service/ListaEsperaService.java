package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.ListaEsperaRequest;
import pe.edu.upeu.lms.dto.ListaEsperaResponse;
import pe.edu.upeu.lms.entity.ListaEspera;
import pe.edu.upeu.lms.entity.SeccionCurso;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.ListaEsperaMapper;
import pe.edu.upeu.lms.repository.ListaEsperaRepository;
import pe.edu.upeu.lms.repository.SeccionCursoRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ListaEsperaService {

    private final ListaEsperaRepository listaEsperaRepository;
    private final ListaEsperaMapper listaEsperaMapper;
    private final SeccionCursoRepository seccionCursoRepository;

    public List<ListaEsperaResponse> listar() {
        return listaEsperaRepository.findAllConSeccionCurso().stream()
                .map(listaEsperaMapper::toResponse)
                .toList();
    }

    public ListaEsperaResponse obtener(Long id) {
        return listaEsperaMapper.toResponse(buscarOFallar(id));
    }

    public ListaEsperaResponse crear(ListaEsperaRequest request) {
        ListaEspera listaEspera = listaEsperaMapper.toEntity(request);
        listaEspera.setSeccionCurso(buscarSeccionCursoOFallar(request.getSeccionCursoId()));
        listaEspera.setFechaRegistro(LocalDateTime.now());
        return listaEsperaMapper.toResponse(listaEsperaRepository.save(listaEspera));
    }

    public ListaEsperaResponse actualizar(Long id, ListaEsperaRequest request) {
        ListaEspera listaEspera = buscarOFallar(id);
        listaEspera.setIdEstudiante(request.getIdEstudiante());
        listaEspera.setPosicion(request.getPosicion());
        listaEspera.setSeccionCurso(buscarSeccionCursoOFallar(request.getSeccionCursoId()));
        return listaEsperaMapper.toResponse(listaEsperaRepository.save(listaEspera));
    }

    public void eliminar(Long id) {
        listaEsperaRepository.delete(buscarOFallar(id));
    }

    private ListaEspera buscarOFallar(Long id) {
        return listaEsperaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lista de espera no encontrada: " + id));
    }

    private SeccionCurso buscarSeccionCursoOFallar(Long seccionCursoId) {
        return seccionCursoRepository.findById(seccionCursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Seccion de curso no encontrada: " + seccionCursoId));
    }
}
