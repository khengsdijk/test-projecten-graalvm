package org.acme.ingredient;

import io.micronaut.context.annotation.Prototype;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;


@Produces
@Prototype
@Requires(classes = {IngredientNotFoundException.class, ExceptionHandler.class})
public class IngredientNotFoundMapper implements ExceptionHandler<IngredientNotFoundException, HttpResponse>{

    @Override
    public HttpResponse handle(HttpRequest request, IngredientNotFoundException exception) {
        return HttpResponse.notFound().body(exception.getMessage());
    }

}