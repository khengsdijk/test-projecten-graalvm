package org.acme;

import io.micronaut.core.annotation.TypeHint;
import io.micronaut.runtime.Micronaut;
import org.hibernate.dialect.PostgreSQL10Dialect;

import java.sql.Driver;

@TypeHint( {
        PostgreSQL10Dialect.class,
        Driver.class
})
public class Application {

    public static void main(String[] args) {

        Micronaut.run(Application.class);
    }
}
