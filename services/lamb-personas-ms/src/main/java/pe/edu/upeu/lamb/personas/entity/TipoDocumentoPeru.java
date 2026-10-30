package pe.edu.upeu.lamb.personas.entity;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Documentos de identidad habituales en Peru (RENIEC, Migraciones, SUNAT) con el formato
 * de su numero. Persona.tipoDocumento guarda el codigo.
 */
public enum TipoDocumentoPeru {

    DNI("Documento Nacional de Identidad", "\\d{8}", "8 digitos"),
    CE("Carne de Extranjeria", "[A-Za-z0-9]{9,12}", "9 a 12 caracteres alfanumericos"),
    PASAPORTE("Pasaporte", "[A-Za-z0-9]{6,12}", "6 a 12 caracteres alfanumericos"),
    RUC("Registro Unico de Contribuyentes", "(10|15|17|20)\\d{9}", "11 digitos que empiezan con 10, 15, 17 o 20"),
    PTP("Permiso Temporal de Permanencia", "[A-Za-z0-9]{9,15}", "9 a 15 caracteres alfanumericos"),
    CPP("Carne de Permiso Temporal de Permanencia", "[A-Za-z0-9]{9,15}", "9 a 15 caracteres alfanumericos"),
    CDI("Cedula de Identidad Diplomatica", "\\d{6,15}", "6 a 15 digitos"),
    PARTIDA("Partida de Nacimiento", "[A-Za-z0-9-]{6,15}", "6 a 15 caracteres");

    private final String nombre;
    private final Pattern formato;
    private final String descripcionFormato;

    TipoDocumentoPeru(String nombre, String formato, String descripcionFormato) {
        this.nombre = nombre;
        this.formato = Pattern.compile(formato);
        this.descripcionFormato = descripcionFormato;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPatron() {
        return formato.pattern();
    }

    public String getDescripcionFormato() {
        return descripcionFormato;
    }

    public boolean esValido(String numero) {
        return numero != null && formato.matcher(numero).matches();
    }

    public static Optional<TipoDocumentoPeru> deCodigo(String codigo) {
        return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(codigo)).findFirst();
    }
}
