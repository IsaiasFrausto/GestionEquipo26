package com.app.ecom.web;

import com.app.ecom.dto.ProductoRequest;
import com.app.ecom.entities.Categoria;
import com.app.ecom.entities.Producto;
import com.app.ecom.repository.CategoriaRepository;
import com.app.ecom.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;


//jdbc:h2:mem:productos-db

/**
 * Controlador GraphQL encargado de manejar las operaciones relacionadas con productos y categorías.
 * 
 * Expone métodos como queries y mutations que son invocados desde el esquema GraphQL.
 */
@Controller
public class ProductoGraphQLController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Query para obtener la lista de todos los productos disponibles.
     * 
     * @return lista de productos.
     */
    @QueryMapping
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    /**
     * Query para obtener un producto por su ID.
     * 
     * @param id identificador del producto.
     * @return el producto correspondiente.
     * @throws RuntimeException si no se encuentra el producto.
     */
    @QueryMapping
    public Producto listarProductoPorId(@Argument String id) {
        return productoRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Producto %s no encontrado", id))
        );
    }

    /**
     * Query para obtener todas las categorías registradas.
     * 
     * @return lista de categorías.
     */
    @QueryMapping
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    /**
     * Query para obtener una categoría por su ID.
     * 
     * @param id identificador de la categoría.
     * @return la categoría correspondiente.
     * @throws RuntimeException si no se encuentra la categoría.
     */
    @QueryMapping
    public Categoria listarCategoriaPorId(@Argument Long id) {
        return categoriaRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Categoria %s no encontrada", id))
        );
    }

    /**
     * Mutation para guardar un nuevo producto en la base de datos.
     * 
     * @param productoRequest objeto que contiene los datos del nuevo producto.
     * @return el producto guardado.
     */
    @MutationMapping
    public Producto guardarProducto(@Argument ProductoRequest productoRequest) {
        Categoria categoria = categoriaRepository.findById(productoRequest.categoriaId()).orElse(null);
        Producto productoBBDD = new Producto();
        productoBBDD.setId(UUID.randomUUID().toString());
        productoBBDD.setNombre(productoRequest.nombre());
        productoBBDD.setPrecio(productoRequest.precio());
        productoBBDD.setCantidad(productoRequest.cantidad());
        productoBBDD.setCategoria(categoria);

        return productoRepository.save(productoBBDD);
    }

    /**
     * Mutation para actualizar un producto existente.
     * 
     * @param id identificador del producto a actualizar.
     * @param productoRequest objeto con los nuevos datos del producto.
     * @return el producto actualizado.
     */
    @MutationMapping
    public Producto actualizarProducto(@Argument String id, @Argument ProductoRequest productoRequest) {
        Categoria categoria = categoriaRepository.findById(productoRequest.categoriaId()).orElse(null);
        Producto productoBBDD = new Producto();
        productoBBDD.setId(id);
        productoBBDD.setNombre(productoRequest.nombre());
        productoBBDD.setPrecio(productoRequest.precio());
        productoBBDD.setCantidad(productoRequest.cantidad());
        productoBBDD.setCategoria(categoria);

        return productoRepository.save(productoBBDD);
    }

    /**
     * Mutation para eliminar un producto por su ID.
     * 
     * @param id identificador del producto a eliminar.
     */
    @MutationMapping
    public void eliminarProducto(@Argument String id) {
        productoRepository.deleteById(id);
    }
}