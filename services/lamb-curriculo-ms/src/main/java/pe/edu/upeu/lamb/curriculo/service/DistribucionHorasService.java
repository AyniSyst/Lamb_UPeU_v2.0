package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.DistribucionHorasRequest;
import pe.edu.upeu.lamb.curriculo.dto.DistribucionHorasResponse;
import pe.edu.upeu.lamb.curriculo.entity.CursoCatalogo;
import pe.edu.upeu.lamb.curriculo.entity.DistribucionHoras;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.DistribucionHorasMapper;
import pe.edu.upeu.lamb.curriculo.repository.CursoCatalogoRepository;
import pe.edu.upeu.lamb.curriculo.repository.DistribucionHorasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistribucionHorasService {

    private final DistribucionHorasRepository distribucionHorasRepository;
    private final CursoCatalogoRepository cursoCatalogoRepository;
    private final DistribucionHorasMapper distribucionHorasMapper;

    @Transactional(readOnly = true)
    public List<DistribucionHorasResponse> listar(Long idCurso) {
        List<DistribucionHoras> lista = idCurso == null
                ? distribucionHorasRepository.findAll()
                : distribucionHorasRepository.findByCursoId(idCurso);
        return lista.stream().map(distribucionHorasMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public DistribucionHorasResponse obtener(Long id) {
        return distribucionHorasMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public DistribucionHorasResponse crear(DistribucionHorasRequest request) {
        validar(request, null);
        DistribucionHoras distribucionHoras = distribucionHorasMapper.toEntity(request);
        asignarRelaciones(distribucionHoras, request);
        return distribucionHorasMapper.toResponse(distribucionHorasRepository.save(distribucionHoras));
    }

    @Transactional
    public DistribucionHorasResponse actualizar(Long id, DistribucionHorasRequest request) {
        DistribucionHoras distribucionHoras = buscarOFallar(id);
        validar(request, id);
        distribucionHoras.setHorasTeoria(request.getHorasTeoria());
        distribucionHoras.setHorasPractica(request.getHorasPractica());
        distribucionHoras.setHorasLaboratorio(request.getHorasLaboratorio());
        asignarRelaciones(distribucionHoras, request);
        return distribucionHorasMapper.toResponse(distribucionHorasRepository.save(distribucionHoras));
    }

    @Transactional
    public void eliminar(Long id) {
        distribucionHorasRepository.delete(buscarOFallar(id));
    }

    DistribucionHoras buscarOFallar(Long id) {
        return distribucionHorasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Distribucion de horas no encontrada: " + id));
    }

    private void validar(DistribucionHorasRequest request, Long id) {
        boolean duplicado = id == null
                ? distribucionHorasRepository.existsByCursoId(request.getIdCurso())
                : distribucionHorasRepository.existsByCursoIdAndIdNot(request.getIdCurso(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de distribucion de horas para esa combinacion de: curso");
        }
    }

    private void asignarRelaciones(DistribucionHoras distribucionHoras, DistribucionHorasRequest request) {
        distribucionHoras.setCurso(cursoCatalogoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso del catalogo no encontrado: " + request.getIdCurso())));
    }
}
