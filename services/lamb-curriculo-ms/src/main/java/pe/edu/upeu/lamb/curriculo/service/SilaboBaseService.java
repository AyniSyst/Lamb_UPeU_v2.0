package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.SilaboBaseRequest;
import pe.edu.upeu.lamb.curriculo.dto.SilaboBaseResponse;
import pe.edu.upeu.lamb.curriculo.entity.CursoCatalogo;
import pe.edu.upeu.lamb.curriculo.entity.SilaboBase;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.SilaboBaseMapper;
import pe.edu.upeu.lamb.curriculo.repository.CursoCatalogoRepository;
import pe.edu.upeu.lamb.curriculo.repository.SilaboBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SilaboBaseService {

    private final SilaboBaseRepository silaboBaseRepository;
    private final CursoCatalogoRepository cursoCatalogoRepository;
    private final SilaboBaseMapper silaboBaseMapper;

    @Transactional(readOnly = true)
    public List<SilaboBaseResponse> listar(Long idCurso) {
        List<SilaboBase> lista = idCurso == null
                ? silaboBaseRepository.findAll()
                : silaboBaseRepository.findByCursoId(idCurso);
        return lista.stream().map(silaboBaseMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SilaboBaseResponse obtener(Long id) {
        return silaboBaseMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SilaboBaseResponse crear(SilaboBaseRequest request) {
        validar(request, null);
        SilaboBase silaboBase = silaboBaseMapper.toEntity(request);
        asignarRelaciones(silaboBase, request);
        return silaboBaseMapper.toResponse(silaboBaseRepository.save(silaboBase));
    }

    @Transactional
    public SilaboBaseResponse actualizar(Long id, SilaboBaseRequest request) {
        SilaboBase silaboBase = buscarOFallar(id);
        validar(request, id);
        silaboBase.setVersion(request.getVersion().trim());
        silaboBase.setCompetencias(request.getCompetencias());
        silaboBase.setMetodologia(request.getMetodologia());
        silaboBase.setEvaluacion(request.getEvaluacion());
        silaboBase.setBibliografia(request.getBibliografia());
        silaboBase.setEstado(request.getEstado());
        asignarRelaciones(silaboBase, request);
        return silaboBaseMapper.toResponse(silaboBaseRepository.save(silaboBase));
    }

    @Transactional
    public void eliminar(Long id) {
        silaboBaseRepository.delete(buscarOFallar(id));
    }

    SilaboBase buscarOFallar(Long id) {
        return silaboBaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Silabo base del curso no encontrado: " + id));
    }

    private void validar(SilaboBaseRequest request, Long id) {
        boolean duplicado = id == null
                ? silaboBaseRepository.existsByCursoIdAndVersion(request.getIdCurso(), request.getVersion().trim())
                : silaboBaseRepository.existsByCursoIdAndVersionAndIdNot(request.getIdCurso(), request.getVersion().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de silabo base del curso para esa combinacion de: curso, version");
        }
    }

    private void asignarRelaciones(SilaboBase silaboBase, SilaboBaseRequest request) {
        silaboBase.setCurso(cursoCatalogoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del catalogo no encontrado: " + request.getIdCurso())));
    }
}
