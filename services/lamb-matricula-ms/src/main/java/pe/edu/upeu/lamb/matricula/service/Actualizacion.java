package pe.edu.upeu.lamb.matricula.service;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Copia sobre la entidad ya guardada los valores no nulos de la entidad construida
 * a partir del request, reutilizando la misma logica de crear() para actualizar().
 * No toca el id, las marcas de creacion ni las colecciones (relaciones uno-a-muchos).
 */
final class Actualizacion {

    private static final Set<String> PROTEGIDOS = Set.of(
            "id", "class", "fechaRegistro", "fechaCreacion", "creadoEn", "createdAt", "version");

    private Actualizacion() {
    }

    static <T> T copiar(T origen, T destino) {
        BeanWrapper desde = new BeanWrapperImpl(origen);
        BeanWrapper hacia = new BeanWrapperImpl(destino);
        for (PropertyDescriptor propiedad : desde.getPropertyDescriptors()) {
            String nombre = propiedad.getName();
            if (PROTEGIDOS.contains(nombre) || !desde.isReadableProperty(nombre) || !hacia.isWritableProperty(nombre)) {
                continue;
            }
            Class<?> tipo = propiedad.getPropertyType();
            if (tipo != null && (Collection.class.isAssignableFrom(tipo) || Map.class.isAssignableFrom(tipo))) {
                continue;
            }
            Object valor = desde.getPropertyValue(nombre);
            if (valor != null) {
                hacia.setPropertyValue(nombre, valor);
            }
        }
        return destino;
    }
}
