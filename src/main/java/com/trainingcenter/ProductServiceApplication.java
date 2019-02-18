package com.trainingcenter;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ProductServiceApplication extends Application<ProductServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ProductServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "ProductService";
    }

    @Override
    public void initialize(final Bootstrap<ProductServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ProductServiceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
