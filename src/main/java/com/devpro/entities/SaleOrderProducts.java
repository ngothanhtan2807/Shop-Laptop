package com.devpro.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_saleorder_products")
@Getter
@Setter
public class SaleOrderProducts extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quality")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "saleorder_id")
    private SaleOrder saleOrder;
}
