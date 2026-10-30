package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.RegistroAsistenciaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.RegistroAsistenciaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.RegistroAsistencia;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.RegistroAsistenciaMapper;
import pe.edu.upeu.lamb.evaluacion.repository.RegistroAsistenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroAsistenciaService {

    private final RegistroAsistenciaRepository registroAsistenciaRepository;
    private final RegistroAsistenciaMapper registroAsistenciaMapper;

    @Transactional(readOnly = true)
    public List<RegistroAsistenciaResponse> listar(Long idGrupo) {
        List<RegistroAsistencia> lista = idGrupo == null
                ? registroAsistenciaRepository.findAll()
                : registroAsistenciaRepository.findByIdGrupo(idGrupo);
        return lista.stream().map(registroAsistenciaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RegistroAsistenciaResponse obtener(Long id) {
        return registroAsistenciaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public RegistroAsistenciaResponse crear(RegistroAsistenciaRequest request) {
        RegistroAsistencia registroAsistencia = registroAsistenciaMapper.toEntity(request);
        return registroAsistenciaMapper.toResponse(registroAsistenciaRepository.save(registroAsistencia));
    }

    @Transactional
    public RegistroAsistenciaResponse actualizar(Long id, RegistroAsistenciaRequest request) {
        RegistroAsistencia registroAsistencia = buscarOFallar(id);
        registroAsistencia.setIdGrupo(request.getIdGrupo());
        registroAsistencia.setIdPersona(request.getIdPersona());
        registroAsistencia.setIdSesionClase(request.getIdSesionClase());
        registroAsistencia.setFecha(request.getFecha());
        registroAsistencia.setEstado(request.getEstado());
        return registroAsistenciaMapper.toResponse(registroAsistenciaRepository.save(registroAsistencia));
    }

    @Transactional
    public void eliminar(Long id) {
        registroAsistenciaRepository.delete(buscarOFallar(id));
    }

    RegistroAsistencia buscarOFallar(Long id) {
        return registroAsistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de asistencia no encontrado: " + id));
    }
}
