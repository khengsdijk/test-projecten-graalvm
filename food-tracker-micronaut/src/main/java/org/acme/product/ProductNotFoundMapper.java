package org.acme.product;

import io.micronaut.context.annotation.Prototype;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

@Produces
@Prototype
@Requires(classes = {ProductNotFoundException.class, ExceptionHandler.class})
public class ProductNotFoundMapper implements ExceptionHandler<ProductNotFoundException, HttpResponse>{

    @Override
    public HttpResponse handle(HttpRequest request, ProductNotFoundException exception) {
        return HttpResponse.notFound().body(exception.getMessage());
    }

}
