package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ingresospagos.dto.FinanciamientoEducativoRequest;
import pe.edu.upeu.ingresospagos.dto.FinanciamientoEducativoResponse;
import pe.edu.upeu.ingresospagos.entity.EntidadFinanciera;
import pe.edu.upeu.ingresospagos.entity.FinanciamientoEducativo;
import pe.edu.upeu.ingresospagos.exception.ResourceNotFoundException;
import pe.edu.upeu.ingresospagos.mapper.FinanciamientoEducativoMapper;
import pe.edu.upeu.ingresospagos.repository.EntidadFinancieraRepository;
import pe.edu.upeu.ingresospagos.repository.FinanciamientoEducativoRepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FinanciamientoEducativoService {

    private final FinanciamientoEducativoRepository financiamientoEducativoRepository;
    private final FinanciamientoEducativoMapper financiamientoEducativoMapper;
    private final EntidadFinancieraRepository entidadFinancieraRepository;

    public List<FinanciamientoEducativoResponse> listar() {
        return financiamientoEducativoRepository.findAllConEntidadFinanciera().stream()
                .map(financiamientoEducativoMapper::toResponse)
                .toList();
    }

    public FinanciamientoEducativoResponse obtener(Long id) {
        return financiamientoEducativoMapper.toResponse(buscarOFallar(id));
    }

    public FinanciamientoEducativoResponse crear(FinanciamientoEducativoRequest request) {
        FinanciamientoEducativo financiamientoEducativo = financiamientoEducativoMapper.toEntity(request);
        financiamientoEducativo.setEntidadFinanciera(buscarEntidadFinancieraOFallar(request.getEntidadFinancieraId()));
        return financiamientoEducativoMapper.toResponse(financiamientoEducativoRepository.save(financiamientoEducativo));
    }

    public FinanciamientoEducativoResponse actualizar(Long id, FinanciamientoEducativoRequest request) {
        FinanciamientoEducativo financiamientoEducativo = buscarOFallar(id);
        financiamientoEducativo.setIdEstudiante(request.getIdEstudiante());
        financiamientoEducativo.setMontoFinanciado(request.getMontoFinanciado());
        financiamientoEducativo.setNumeroCuotas(request.getNumeroCuotas());
        financiamientoEducativo.setFechaInicio(request.getFechaInicio());
        financiamientoEducativo.setActivo(request.getActivo());
        financiamientoEducativo.setEntidadFinanciera(buscarEntidadFinancieraOFallar(request.getEntidadFinancieraId()));
        return financiamientoEducativoMapper.toResponse(financiamientoEducativoRepository.save(financiamientoEducativo));
    }

    public void eliminar(Long id) {
        financiamientoEducativoRepository.delete(buscarOFallar(id));
    }

    private FinanciamientoEducativo buscarOFallar(Long id) {
        return financiamientoEducativoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Financiamiento educativo no encontrado: " + id));
    }

    private EntidadFinanciera buscarEntidadFinancieraOFallar(Long entidadFinancieraId) {
        return entidadFinancieraRepository.findById(entidadFinancieraId)
                .orElseThrow(() -> new ResourceNotFoundException("Entidad financiera no encontrada: " + entidadFinancieraId));
    }
}
