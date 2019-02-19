package com.trainingcenter;

import com.trainingcenter.db.ProductDao;
import com.trainingcenter.resources.ProductResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

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
        bootstrap.addBundle(new MigrationsBundle<ProductServiceConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ProductServiceConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final ProductServiceConfiguration configuration,
                    final Environment environment) {

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");
        ProductDao productDao = jdbi.onDemand(ProductDao.class);

        //Resources
        ProductResource productResource = new ProductResource(productDao);
        environment.jersey().register(productResource);

    }

}
