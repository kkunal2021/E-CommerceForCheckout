package com.kunal.ecommerce.internal.repository;

import com.kunal.ecommerce.internal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author kunal
 * @project ECommerceCheckout
 */

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, String> {

    @Override
    public Product save (Product product);

    @Override
    public List<Product> findAll();

    @Override
    public Product getOne (String name);
}
