package org.acme.supplier;



import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {SupplierNotfoundException.class, ExceptionHandler.class})
public class SupplierNotFoundHandler implements ExceptionHandler<SupplierNotfoundException, HttpResponse>{

    @Override
    public HttpResponse handle(HttpRequest request, SupplierNotfoundException exception) {
        return HttpResponse.notFound().body(exception.getMessage());
    }

}
