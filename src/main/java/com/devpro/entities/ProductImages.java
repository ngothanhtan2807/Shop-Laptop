package com.devpro.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_products_images")
@Getter
@Setter
public class ProductImages extends BaseEntity {

    @Column(name = "title", length = 45, nullable = false)
    private String title;

    @Column(name = "path", length = 200, nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
