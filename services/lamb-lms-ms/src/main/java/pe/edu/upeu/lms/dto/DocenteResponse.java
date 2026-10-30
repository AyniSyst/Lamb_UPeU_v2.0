package pe.edu.upeu.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocenteResponse {
    private Long id;
    private String nombres;
    private String apellidos;
    private String email;
    private String especialidad;
    private Boolean activo;
}
