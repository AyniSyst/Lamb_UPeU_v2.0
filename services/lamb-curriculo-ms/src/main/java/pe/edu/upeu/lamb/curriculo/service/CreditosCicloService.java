package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.CreditosCicloRequest;
import pe.edu.upeu.lamb.curriculo.dto.CreditosCicloResponse;
import pe.edu.upeu.lamb.curriculo.entity.CicloPlan;
import pe.edu.upeu.lamb.curriculo.entity.CreditosCiclo;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.CreditosCicloMapper;
import pe.edu.upeu.lamb.curriculo.repository.CicloPlanRepository;
import pe.edu.upeu.lamb.curriculo.repository.CreditosCicloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditosCicloService {

    private final CreditosCicloRepository creditosCicloRepository;
    private final CicloPlanRepository cicloPlanRepository;
    private final CreditosCicloMapper creditosCicloMapper;

    @Transactional(readOnly = true)
    public List<CreditosCicloResponse> listar(Long idCicloPlan) {
        List<CreditosCiclo> lista = idCicloPlan == null
                ? creditosCicloRepository.findAll()
                : creditosCicloRepository.findByCicloPlanId(idCicloPlan);
        return lista.stream().map(creditosCicloMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CreditosCicloResponse obtener(Long id) {
        return creditosCicloMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public CreditosCicloResponse crear(CreditosCicloRequest request) {
        validar(request, null);
        CreditosCiclo creditosCiclo = creditosCicloMapper.toEntity(request);
        asignarRelaciones(creditosCiclo, request);
        return creditosCicloMapper.toResponse(creditosCicloRepository.save(creditosCiclo));
    }

    @Transactional
    public CreditosCicloResponse actualizar(Long id, CreditosCicloRequest request) {
        CreditosCiclo creditosCiclo = buscarOFallar(id);
        validar(request, id);
        creditosCiclo.setCreditosMinimos(request.getCreditosMinimos());
        creditosCiclo.setCreditosMaximos(request.getCreditosMaximos());
        asignarRelaciones(creditosCiclo, request);
        return creditosCicloMapper.toResponse(creditosCicloRepository.save(creditosCiclo));
    }

    @Transactional
    public void eliminar(Long id) {
        creditosCicloRepository.delete(buscarOFallar(id));
    }

    CreditosCiclo buscarOFallar(Long id) {
        return creditosCicloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Creditos del ciclo no encontrado: " + id));
    }

    private void validar(CreditosCicloRequest request, Long id) {
        if (request.getCreditosMaximos() < request.getCreditosMinimos()) {
            throw new BusinessRuleException("Creditos maximos no puede ser menor que creditos minimos");
        }
        boolean duplicado = id == null
                ? creditosCicloRepository.existsByCicloPlanId(request.getIdCicloPlan())
                : creditosCicloRepository.existsByCicloPlanIdAndIdNot(request.getIdCicloPlan(), id);
        if (duplicado) {
            throw new BusinessRuleException("Ya existe un registro de creditos del ciclo para esa combinacion de: ciclo plan");
        }
    }

    private void asignarRelaciones(CreditosCiclo creditosCiclo, CreditosCicloRequest request) {
        creditosCiclo.setCicloPlan(cicloPlanRepository.findById(request.getIdCicloPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Ciclo del plan no encontrado: " + request.getIdCicloPlan())));
    }
}
