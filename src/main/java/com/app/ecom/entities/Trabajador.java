package com.app.ecom.entities;

//Equipo 26 
//Iñaki González | César Mecinas | Ernesto Puga | Sayid Valdivias | Rafael Romo
//Fecha: 26_05_2025

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Trabajador {

    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String rol;
    private String email;

    @ManyToOne
    private EquipoDeTrabajo equipo;
}
