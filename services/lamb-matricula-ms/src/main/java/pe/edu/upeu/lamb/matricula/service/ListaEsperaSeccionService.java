package pe.edu.upeu.lamb.matricula.service;

import pe.edu.upeu.lamb.matricula.dto.ListaEsperaSeccionRequest;
import pe.edu.upeu.lamb.matricula.dto.ListaEsperaSeccionResponse;
import pe.edu.upeu.lamb.matricula.entity.ListaEsperaSeccion;
import pe.edu.upeu.lamb.matricula.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.matricula.mapper.ListaEsperaSeccionMapper;
import pe.edu.upeu.lamb.matricula.repository.ListaEsperaSeccionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ListaEsperaSeccionService {

    private final ListaEsperaSeccionRepository listaEsperaSeccionRepository;
    private final ListaEsperaSeccionMapper listaEsperaSeccionMapper;

    public List<ListaEsperaSeccionResponse> listar() {
        return listaEsperaSeccionRepository.findAll().stream()
                .map(listaEsperaSeccionMapper::toResponse)
                .toList();
    }

    public ListaEsperaSeccionResponse obtener(Long id) {
        return listaEsperaSeccionMapper.toResponse(buscarOFallar(id));
    }

    public ListaEsperaSeccionResponse crear(ListaEsperaSeccionRequest request) {
        ListaEsperaSeccion entity = listaEsperaSeccionMapper.toEntity(request);
        return listaEsperaSeccionMapper.toResponse(listaEsperaSeccionRepository.save(entity));
    }

    public ListaEsperaSeccionResponse actualizar(Long id, ListaEsperaSeccionRequest request) {
        ListaEsperaSeccion existente = buscarOFallar(id);
        ListaEsperaSeccion entity = listaEsperaSeccionMapper.toEntity(request);
        return listaEsperaSeccionMapper.toResponse(listaEsperaSeccionRepository.save(Actualizacion.copiar(entity, existente)));
    }

    public void eliminar(Long id) {
        listaEsperaSeccionRepository.delete(buscarOFallar(id));
    }

    private ListaEsperaSeccion buscarOFallar(Long id) {
        return listaEsperaSeccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lista de espera de seccion no encontrada: " + id));
    }
}
