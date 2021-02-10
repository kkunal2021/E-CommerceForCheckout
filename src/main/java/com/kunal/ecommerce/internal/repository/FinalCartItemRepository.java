/*
 * (C) Copyright ${year} Mauro Mozzarelli.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     ...
 */
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
