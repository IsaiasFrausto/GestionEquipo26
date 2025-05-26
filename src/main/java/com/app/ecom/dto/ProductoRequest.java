package com.app.ecom.dto;

/**
Una clase record en Java es un tipo de clase especial e inmutable introducida en 
Java 14 (como preview) y oficialmente en Java 16, diseñada para reducir el código
repetitivo al trabajar con objetos que solo contienen datos. 
 */
public record ProductoRequest(
        String id,           
        String nombre,       
        double precio,       
        int cantidad,        
        Long categoriaId    
) {
}
