//Equipo 26 
//Iñaki González | César Mecinas | Ernesto Puga | Sayid Valdivias | Rafael Romo
//Fecha: 26_05_2025
package com.app.ecom.dto;

/**
Una clase record en Java es un tipo de clase especial e inmutable introducida en 
Java 14 (como preview) y oficialmente en Java 16, diseñada para reducir el código
repetitivo al trabajar con objetos que solo contienen datos. 
 */
public record TrabajadorRequest(
        String id,
        String nombre,
        String apellido,
        String rol,
        String email,
        Long EquipoDeTrabajoId    
) {
}
