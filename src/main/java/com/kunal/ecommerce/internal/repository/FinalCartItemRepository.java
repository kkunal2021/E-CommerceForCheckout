package com.kunal.ecommerce.internal.repository;

import com.kunal.ecommerce.internal.entity.FinalCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author kunal
 * @project ECommerceCheckout
 */

@Repository
@Transactional
public interface FinalCartItemRepository extends JpaRepository<FinalCartItem, String> {

    @Override
    public FinalCartItem save(FinalCartItem trolleyItem);

    @Override
    public List<FinalCartItem> findAll();

    @Override
    public FinalCartItem getOne(String item);

    @Query(value = "SELECT * FROM FINAL_CART_ITEM ORDER by offer, price ASC", nativeQuery = true)
    List<FinalCartItem> findAllOrderByOfferPriceAsc();
}
