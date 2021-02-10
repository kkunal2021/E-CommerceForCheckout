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
@Table(name = "OFFER")
public class Offer {

    @Id
    private String code;

    @Column(name = "COUPON_LIFE", length = 50)
    private int couponThreshold = 0;

}
