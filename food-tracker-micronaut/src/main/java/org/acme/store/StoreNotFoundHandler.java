package org.acme.store;

import io.micronaut.context.annotation.Prototype;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;


@Produces
@Prototype
@Requires(classes = {StoreNotFoundException.class, ExceptionHandler.class})
public class StoreNotFoundHandler implements ExceptionHandler<StoreNotFoundException, HttpResponse>{

    @Override
    public HttpResponse handle(HttpRequest request, StoreNotFoundException exception) {
        return HttpResponse.notFound().body(exception.getMessage());
    }

}