//Equipo 26 
//Iñaki González | César Mecinas | Ernesto Puga | Sayid Valdivias | Rafael Romo
//Fecha: 26_05_2025

package com.app.ecom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

/**
 Entidad que representa una categoría de productos en el sistema.
 */
@Entity // Marca esta clase como una entidad JPA para mapearla a una tabla de base de datos.
@AllArgsConstructor // Genera un constructor con todos los campos.
@NoArgsConstructor // Genera un constructor vacío (por defecto).
@Data // Genera getters, setters, toString(), equals() y hashCode() automáticamente.
@Builder // Permite construir objetos Categoria usando el patrón Builder.
public class EquipoDeTrabajo {

    /**
     * Identificador único de la categoría.
     * Se genera automáticamente usando la estrategia de incremento automático (IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;         // Nombre del equipo
    private String proyecto;       // Nombre del proyecto en el que trabaja
    private String lider;          // Nombre del líder del equipo
    private LocalDate fechaCreacion; // Fecha de creación del equipo
    private String descripcion;    // Breve descripción del propósito del equipo

    @OneToMany(mappedBy = "equipo")
    private List<Trabajador> trabajadores;
}
