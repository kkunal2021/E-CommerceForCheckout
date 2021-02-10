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
package com.kunal.ecommerce.internal.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author kunal
 * @project ECommerceCheckout
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "FINAL_CART_ITEM")
public class FinalCartItem {

    @Id
    private String watchId;

    @Column(name = "WATCH_NAME", length = 50)
    private String watchName;

    @Column(name = "WATCH_UNIT_PRICE", length = 50)
    private BigDecimal unitPrice;

    @Column(name = "DISCOUNT", length = 50)
    private int discount;

    @Column(name = "OFFER", length = 50)
    private String offer;
}
