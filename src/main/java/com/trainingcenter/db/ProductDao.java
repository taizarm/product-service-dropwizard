package com.trainingcenter.db;

import com.trainingcenter.api.Product;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(ProductMapper.class)
public interface ProductDao {

    @SqlUpdate("insert into product (name, description) values (:name, :description)")
    void insert(@BindBean Product product);

    @SqlQuery("select * from product")
    List<Product> getAllProducts();

    @SqlQuery("select * from product where id = :id")
    Product findById(@Bind("id") int id);
}
