package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.CompetenciaEspecificaRequest;
import pe.edu.upeu.lamb.curriculo.dto.CompetenciaEspecificaResponse;
import pe.edu.upeu.lamb.curriculo.entity.CompetenciaEspecifica;
import pe.edu.upeu.lamb.curriculo.entity.PerfilEgreso;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.CompetenciaEspecificaMapper;
import pe.edu.upeu.lamb.curriculo.repository.CompetenciaEspecificaRepository;
import pe.edu.upeu.lamb.curriculo.repository.PerfilEgresoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetenciaEspecificaService {

    private final CompetenciaEspecificaRepository competenciaEspecificaRepository;
    private final PerfilEgresoRepository perfilEgresoRepository;
    private final CompetenciaEspecificaMapper competenciaEspecificaMapper;

    @Transactional(readOnly = true)
    public List<CompetenciaEspecificaResponse> listar(Long idPerfilEgreso) {
        List<CompetenciaEspecifica> lista = idPerfilEgreso == null
                ? competenciaEspecificaRepository.findAll()
                : competenciaEspecificaRepository.findByPerfilEgresoId(idPerfilEgreso);
        return lista.stream().map(competenciaEspecificaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CompetenciaEspecificaResponse obtener(Long id) {
        return competenciaEspecificaMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CompetenciaEspecificaResponse crear(CompetenciaEspecificaRequest request) {
        validar(request, null);
        CompetenciaEspecifica competenciaEspecifica = competenciaEspecificaMapper.toEntity(request);
        asignarRelaciones(competenciaEspecifica, request);
        return competenciaEspecificaMapper.toResponse(competenciaEspecificaRepository.save(competenciaEspecifica));
    }

    @Transactional
    public CompetenciaEspecificaResponse actualizar(Long id, CompetenciaEspecificaRequest request) {
        CompetenciaEspecifica competenciaEspecifica = buscarOFallar(id);
        validar(request, id);
        competenciaEspecifica.setCodigo(request.getCodigo().trim());
        competenciaEspecifica.setNombre(request.getNombre().trim());
        competenciaEspecifica.setDescripcion(request.getDescripcion());
        asignarRelaciones(competenciaEspecifica, request);
        return competenciaEspecificaMapper.toResponse(competenciaEspecificaRepository.save(competenciaEspecifica));
    }

    @Transactional
    public void eliminar(Long id) {
        competenciaEspecificaRepository.delete(buscarOFallar(id));
    }

    CompetenciaEspecifica buscarOFallar(Long id) {
        return competenciaEspecificaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Competencia especifica no encontrada: " + id));
    }

    private void validar(CompetenciaEspecificaRequest request, Long id) {
        boolean duplicado = id == null
                ? competenciaEspecificaRepository.existsByPerfilEgresoIdAndCodigo(request.getIdPerfilEgreso(), request.getCodigo().trim())
                : competenciaEspecificaRepository.existsByPerfilEgresoIdAndCodigoAndIdNot(request.getIdPerfilEgreso(), request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de competencia especifica para esa combinacion de: perfil egreso, codigo");
        }
    }

    private void asignarRelaciones(CompetenciaEspecifica competenciaEspecifica, CompetenciaEspecificaRequest request) {
        competenciaEspecifica.setPerfilEgreso(perfilEgresoRepository.findById(request.getIdPerfilEgreso())
                .orElseThrow(() -> new ResourceNotFoundException("Perfil de egreso no encontrado: " + request.getIdPerfilEgreso())));
    }
}
