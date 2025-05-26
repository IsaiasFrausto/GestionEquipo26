package com.app.ecom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 Entidad que representa una categoría de productos en el sistema.
 */
@Entity // Marca esta clase como una entidad JPA para mapearla a una tabla de base de datos.
@AllArgsConstructor // Genera un constructor con todos los campos.
@NoArgsConstructor // Genera un constructor vacío (por defecto).
@Data // Genera getters, setters, toString(), equals() y hashCode() automáticamente.
@Builder // Permite construir objetos Categoria usando el patrón Builder.
public class Categoria {

    /**
     * Identificador único de la categoría.
     * Se genera automáticamente usando la estrategia de incremento automático (IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la categoría (ej. "Computadoras", "Smartphones", etc.).
     */
    private String nombre;

    /**
     * Lista de productos que pertenecen a esta categoría.
     * Relación uno-a-muchos: una categoría puede tener muchos productos.
     * El campo 'categoria' en la clase Producto es el dueño de la relación.
     */
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;
}
