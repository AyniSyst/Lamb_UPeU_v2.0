package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.MallaCurricularRequest;
import pe.edu.upeu.lms.dto.MallaCurricularResponse;
import pe.edu.upeu.lms.entity.MallaCurricular;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.MallaCurricularMapper;
import pe.edu.upeu.lms.repository.MallaCurricularRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MallaCurricularService {

    private final MallaCurricularRepository mallaCurricularRepository;
    private final MallaCurricularMapper mallaCurricularMapper;

    public List<MallaCurricularResponse> listar() {
        return mallaCurricularRepository.findAll().stream()
                .map(mallaCurricularMapper::toResponse)
                .toList();
    }

    public MallaCurricularResponse obtener(Long id) {
        return mallaCurricularMapper.toResponse(buscarOFallar(id));
    }

    public MallaCurricularResponse crear(MallaCurricularRequest request) {
        MallaCurricular mallaCurricular = mallaCurricularMapper.toEntity(request);
        return mallaCurricularMapper.toResponse(mallaCurricularRepository.save(mallaCurricular));
    }

    public MallaCurricularResponse actualizar(Long id, MallaCurricularRequest request) {
        MallaCurricular mallaCurricular = buscarOFallar(id);
        mallaCurricular.setPrograma(request.getPrograma());
        mallaCurricular.setNombre(request.getNombre());
        mallaCurricular.setVersion(request.getVersion());
        mallaCurricular.setActivo(request.getActivo());
        return mallaCurricularMapper.toResponse(mallaCurricularRepository.save(mallaCurricular));
    }

    public void eliminar(Long id) {
        mallaCurricularRepository.delete(buscarOFallar(id));
    }

    private MallaCurricular buscarOFallar(Long id) {
        return mallaCurricularRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Malla curricular no encontrada: " + id));
    }
}
