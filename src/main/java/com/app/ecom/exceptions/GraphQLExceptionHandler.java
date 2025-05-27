//Equipo 26 
//Iñaki González | César Mecinas | Ernesto Puga | Sayid Valdivias | Rafael Romo
//Fecha: 26_05_2025
package com.app.ecom.exceptions;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manejador global de excepciones para GraphQL en una aplicación Spring.
 * 
 * Esta clase extiende `DataFetcherExceptionResolverAdapter` y se encarga de interceptar
 * las excepciones que ocurren durante la ejecución de los data fetchers (resolutores de datos)
 * en las consultas y mutaciones de GraphQL.
 * 
 * Permite personalizar cómo se devuelven los errores al cliente GraphQL.
 */
@Component // Marca la clase como un componente de Spring para que sea detectada automáticamente.
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    /**
     * Método que captura una excepción y la transforma en un objeto GraphQLError.
     * 
     * @param ex Excepción lanzada durante la resolución de datos.
     * @param env Entorno de ejecución de GraphQL donde ocurrió el error.
     * @return Un objeto GraphQLError que será devuelto al cliente.
     */
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return new GraphQLError() {

            /**
             * Retorna el mensaje de la excepción como mensaje de error GraphQL.
             */
            @Override
            public String getMessage() {
                return ex.getMessage();
            }

            /**
             * Ubicación en el documento GraphQL donde ocurrió el error.
             * En este caso, se retorna null, pero puede ser personalizado si se desea.
             */
            @Override
            public List<SourceLocation> getLocations() {
                return null;
            }

            /**
             * Clasificación del tipo de error.
             * En este ejemplo, no se especifica (se retorna null).
             */
            @Override
            public ErrorClassification getErrorType() {
                return null;
            }
        };
    }
}