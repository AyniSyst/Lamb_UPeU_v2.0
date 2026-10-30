package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.PeriodoAcademicoRequest;
import pe.edu.upeu.lms.dto.PeriodoAcademicoResponse;
import pe.edu.upeu.lms.entity.PeriodoAcademico;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.PeriodoAcademicoMapper;
import pe.edu.upeu.lms.repository.PeriodoAcademicoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PeriodoAcademicoService {

    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final PeriodoAcademicoMapper periodoAcademicoMapper;

    public List<PeriodoAcademicoResponse> listar() {
        return periodoAcademicoRepository.findAll().stream()
                .map(periodoAcademicoMapper::toResponse)
                .toList();
    }

    public PeriodoAcademicoResponse obtener(Long id) {
        return periodoAcademicoMapper.toResponse(buscarOFallar(id));
    }

    public PeriodoAcademicoResponse crear(PeriodoAcademicoRequest request) {
        PeriodoAcademico periodoAcademico = periodoAcademicoMapper.toEntity(request);
        return periodoAcademicoMapper.toResponse(periodoAcademicoRepository.save(periodoAcademico));
    }

    public PeriodoAcademicoResponse actualizar(Long id, PeriodoAcademicoRequest request) {
        PeriodoAcademico periodoAcademico = buscarOFallar(id);
        periodoAcademico.setNombre(request.getNombre());
        periodoAcademico.setFechaInicio(request.getFechaInicio());
        periodoAcademico.setFechaFin(request.getFechaFin());
        periodoAcademico.setActivo(request.getActivo());
        return periodoAcademicoMapper.toResponse(periodoAcademicoRepository.save(periodoAcademico));
    }

    public void eliminar(Long id) {
        periodoAcademicoRepository.delete(buscarOFallar(id));
    }

    private PeriodoAcademico buscarOFallar(Long id) {
        return periodoAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo academico no encontrado: " + id));
    }
}
