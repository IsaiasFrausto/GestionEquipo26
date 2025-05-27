package com.app.ecom.web;

//Equipo 26 
//Iñaki González | César Mecinas | Ernesto Puga | Sayid Valdivias | Rafael Romo
//Fecha: 26_05_2025

import com.app.ecom.dto.TrabajadorRequest;
import com.app.ecom.entities.EquipoDeTrabajo;
import com.app.ecom.entities.Trabajador;
import com.app.ecom.repository.EquipoDeTrabajoRepository;
import com.app.ecom.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;


//jdbc:h2:mem:Trabajadors-db

/**
 * Controlador GraphQL encargado de manejar las operaciones relacionadas con Trabajadors y categorías.
 * 
 * Expone métodos como queries y mutations que son invocados desde el esquema GraphQL.
 */
@Controller
public class TrabajadorGraphQLController {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private EquipoDeTrabajoRepository equipoDeTrabajoRepository;

    /**
     * Query para obtener la lista de todos los Trabajadors disponibles.
     * 
     * @return lista de Trabajadors.
     */
    @QueryMapping
    public List<Trabajador> listarTrabajadors() {
        return trabajadorRepository.findAll();
    }

    /**
     * Query para obtener un Trabajador por su ID.
     * 
     * @param id identificador del Trabajador.
     * @return el Trabajador correspondiente.
     * @throws RuntimeException si no se encuentra el Trabajador.
     */
    @QueryMapping
    public Trabajador listarTrabajadorPorId(@Argument String id) {
        return trabajadorRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Trabajador %s no encontrado", id))
        );
    }

    /**
     * Query para obtener todas las categorías registradas.
     * 
     * @return lista de categorías.
     */
    @QueryMapping
    public List<EquipoDeTrabajo> listarEquipoDeTrabajos() {
        return equipoDeTrabajoRepository.findAll();
    }

    /**
     * Query para obtener una categoría por su ID.
     * 
     * @param id identificador de la categoría.
     * @return la categoría correspondiente.
     * @throws RuntimeException si no se encuentra la categoría.
     */
    @QueryMapping
    public EquipoDeTrabajo listarEquipoDeTrabajoPorId(@Argument Long id) {
        return equipoDeTrabajoRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Equipo %s no encontrada", id))
        );
    }

    /**
     * Mutation para guardar un nuevo Trabajador en la base de datos.
     * 
     * @param trabajadorRequest objeto que contiene los datos del nuevo Trabajador.
     * @return el Trabajador guardado.
     */
    @MutationMapping
    public Trabajador guardarTrabajador(@Argument TrabajadorRequest trabajadorRequest) {
        EquipoDeTrabajo equipo = equipoDeTrabajoRepository.findById(trabajadorRequest.EquipoDeTrabajoId()).orElse(null);
        Trabajador trabajadorBBDD = new Trabajador();
        trabajadorBBDD.setId(UUID.randomUUID().toString());
        trabajadorBBDD.setNombre(trabajadorRequest.nombre());
        trabajadorBBDD.setApellido(trabajadorRequest.apellido());
        trabajadorBBDD.setEmail(trabajadorRequest.email());
        trabajadorBBDD.setRol(trabajadorRequest.rol());
        trabajadorBBDD.setEquipo(equipo);

        return trabajadorRepository.save(trabajadorBBDD);
    }

    /**
     * Mutation para actualizar un Trabajador existente.
     * 
     * @param id identificador del Trabajador a actualizar.
     * @param trabajadorRequest objeto con los nuevos datos del Trabajador.
     * @return el Trabajador actualizado.
     */
    @MutationMapping
    public Trabajador actualizarTrabajador(@Argument String id, @Argument TrabajadorRequest trabajadorRequest) {
        EquipoDeTrabajo equipo = equipoDeTrabajoRepository.findById(trabajadorRequest.EquipoDeTrabajoId()).orElse(null);
        Trabajador trabajadorBBDD = new Trabajador();
        trabajadorBBDD.setId(id);
        trabajadorBBDD.setNombre(trabajadorRequest.nombre());
        trabajadorBBDD.setApellido(trabajadorRequest.apellido());
        trabajadorBBDD.setEmail(trabajadorRequest.email());
        trabajadorBBDD.setRol(trabajadorRequest.rol());
        trabajadorBBDD.setEquipo(equipo);

        

        return trabajadorRepository.save(trabajadorBBDD);
    }

    /**
     * Mutation para eliminar un Trabajador por su ID.
     * 
     * @param id identificador del Trabajador a eliminar.
     */
    @MutationMapping
    public void eliminarTrabajador(@Argument String id) {
        trabajadorRepository.deleteById(id);
    }
}