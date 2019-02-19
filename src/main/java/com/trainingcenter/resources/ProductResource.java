package com.trainingcenter.resources;

import com.trainingcenter.api.Product;
import com.trainingcenter.db.ProductDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    ProductDao productDao;

    public ProductResource(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GET
    public Response getProducts() {
        List<Product> allProducts = productDao.getAllProducts();
        return Response.ok(allProducts).build();
    }

    @POST
    public Response createProduct(Product product) {
        productDao.insert(product);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") int id) {
        Product product = productDao.findById(id);

        if (product == null)
            throw new WebApplicationException("Product does not exist", Response.Status.NOT_FOUND);

        return Response.ok(product).build();
    }
}
