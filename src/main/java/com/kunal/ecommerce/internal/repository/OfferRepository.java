package com.kunal.ecommerce.internal.repository;

import com.kunal.ecommerce.internal.entity.Offer;
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
public interface OfferRepository extends JpaRepository<Offer, String> {

    @Override
    public Offer save(Offer offer);

    @Override
    public List<Offer> findAll();

    @Override
    public Offer getOne(String code);
}
