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
