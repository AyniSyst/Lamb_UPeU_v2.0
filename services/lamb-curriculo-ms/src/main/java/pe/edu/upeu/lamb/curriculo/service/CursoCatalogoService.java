package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.CursoCatalogoRequest;
import pe.edu.upeu.lamb.curriculo.dto.CursoCatalogoResponse;
import pe.edu.upeu.lamb.curriculo.entity.CursoCatalogo;
import pe.edu.upeu.lamb.curriculo.entity.DepartamentoAcademico;
import pe.edu.upeu.lamb.curriculo.entity.Modalidad;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.CursoCatalogoMapper;
import pe.edu.upeu.lamb.curriculo.repository.CursoCatalogoRepository;
import pe.edu.upeu.lamb.curriculo.repository.DepartamentoAcademicoRepository;
import pe.edu.upeu.lamb.curriculo.repository.ModalidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoCatalogoService {

    private final CursoCatalogoRepository cursoCatalogoRepository;
    private final DepartamentoAcademicoRepository departamentoAcademicoRepository;
    private final ModalidadRepository modalidadRepository;
    private final CursoCatalogoMapper cursoCatalogoMapper;

    @Transactional(readOnly = true)
    public List<CursoCatalogoResponse> listar(Long idDepartamento) {
        List<CursoCatalogo> lista = idDepartamento == null
                ? cursoCatalogoRepository.findAll()
                : cursoCatalogoRepository.findByDepartamentoId(idDepartamento);
        return lista.stream().map(cursoCatalogoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CursoCatalogoResponse obtener(Long id) {
        return cursoCatalogoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CursoCatalogoResponse crear(CursoCatalogoRequest request) {
        validar(request, null);
        CursoCatalogo cursoCatalogo = cursoCatalogoMapper.toEntity(request);
        asignarRelaciones(cursoCatalogo, request);
        return cursoCatalogoMapper.toResponse(cursoCatalogoRepository.save(cursoCatalogo));
    }

    @Transactional
    public CursoCatalogoResponse actualizar(Long id, CursoCatalogoRequest request) {
        CursoCatalogo cursoCatalogo = buscarOFallar(id);
        validar(request, id);
        cursoCatalogo.setCodigo(request.getCodigo().trim());
        cursoCatalogo.setNombre(request.getNombre().trim());
        cursoCatalogo.setCreditos(request.getCreditos());
        asignarRelaciones(cursoCatalogo, request);
        return cursoCatalogoMapper.toResponse(cursoCatalogoRepository.save(cursoCatalogo));
    }

    @Transactional
    public void eliminar(Long id) {
        cursoCatalogoRepository.delete(buscarOFallar(id));
    }

    CursoCatalogo buscarOFallar(Long id) {
        return cursoCatalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso del catalogo no encontrado: " + id));
    }

    private void validar(CursoCatalogoRequest request, Long id) {
        boolean duplicado = id == null
                ? cursoCatalogoRepository.existsByCodigo(request.getCodigo().trim())
                : cursoCatalogoRepository.existsByCodigoAndIdNot(request.getCodigo().trim(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de curso del catalogo para esa combinacion de: codigo");
        }
    }

    private void asignarRelaciones(CursoCatalogo cursoCatalogo, CursoCatalogoRequest request) {
        cursoCatalogo.setDepartamento(request.getIdDepartamento() == null ? null : departamentoAcademicoRepository.findById(request.getIdDepartamento())
                .orElseThrow(() -> new ResourceNotFoundException("Departamento academico no encontrado: " + request.getIdDepartamento())));
        cursoCatalogo.setModalidad(request.getIdModalidad() == null ? null : modalidadRepository.findById(request.getIdModalidad())
                .orElseThrow(() -> new ResourceNotFoundException("Modalidad no encontrada: " + request.getIdModalidad())));
    }
}
