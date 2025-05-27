//Equipo 26 
//Iñaki González | César Mecinas | Ernesto Puga | Sayid Valdivias | Rafael Romo
//Fecha: 26_05_2025
package com.app.ecom;

import com.app.ecom.entities.EquipoDeTrabajo;
import com.app.ecom.entities.Trabajador;
import com.app.ecom.repository.EquipoDeTrabajoRepository;
import com.app.ecom.repository.TrabajadorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.UUID;


//jdbc:h2:mem:productos-db

@SpringBootApplication
public class InventarioServiceGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioServiceGraphqlApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EquipoDeTrabajoRepository equipoDeTrabajoRepository, TrabajadorRepository trabajadorRepository){
		Random random = new Random();
		return args -> {
			List.of("Computadoras","Impresoras","Smartphones").forEach(cat -> {
				EquipoDeTrabajo equipo = EquipoDeTrabajo.builder().nombre(cat).build();
				equipoDeTrabajoRepository.save(equipo);
			});
			equipoDeTrabajoRepository.findAll().forEach(equipo -> {
				for(int i = 0;i < 10; i++){
					Trabajador trabajador = Trabajador.builder()
							.id(UUID.randomUUID().toString())
							.nombre("Sayid "+i)
							.apellido("Valdivia "+i)
							.rol("Developer "+i)
							.email("sayid.valdivia" + i + "@gmail.com")
							.equipo(equipo)
							.build();
					trabajadorRepository.save(trabajador);
				}
			});
		};
	}
}
