package com.kunal.ecommerce.internal.repository;

import com.kunal.ecommerce.internal.entity.ItemsCheckout;
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
public interface ItemsCheckoutRepository extends JpaRepository<ItemsCheckout, String> {

    @Override
    public ItemsCheckout save(ItemsCheckout trolleyItem);

    @Override
    public List<ItemsCheckout> findAll();

    @Override
    public ItemsCheckout getOne(String item);

    @Query(value = "SELECT * FROM ITEMS_CHECKOUT ORDER by offer, price ASC", nativeQuery = true)
    List<ItemsCheckout> findAllOrderByOfferPriceAsc();

    @Query(value = "SELECT * FROM ITEMS_CHECKOUT WHERE offer = ?1 ORDER by offer, price ASC", nativeQuery = true)
    List<ItemsCheckout> findByOfferOrderOfferPriceAsc(String offer);

}
