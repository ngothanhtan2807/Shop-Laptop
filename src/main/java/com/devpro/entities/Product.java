package com.devpro.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "tbl_products")
@Getter
@Setter
public class Product extends BaseEntity {
    @Column(name = "title", length = 500, nullable = false)
    private String title;

    @Column(name = "price", precision = 13, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "short_description", length = 3000, nullable = false)
    private String shortDes;

    @Column(name = "detail_description", length = 500, nullable = false, columnDefinition = "text")
    private String shortDetails;

    @Transient
    private String priceVN;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id") // tên field khoá ngoại
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product"
            , fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ProductImages> productImages = new ArrayList<ProductImages>();

    @Column(name = "seo", nullable = false)
    private String seo;

    public void addProductImages(ProductImages productImages1) {
        productImages1.setProduct(this);
        productImages.add(productImages1);
    }

    public void clearProductImages() {
        for (ProductImages productImages : productImages) {
            productImages.setProduct(null);
        }
        productImages.clear();
    }

    @Transient
    public String getPriceVN() {
        Locale locale = new Locale("vi", "VN");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        return fmt.format(price);
    }

    @Transient
    public void setPriceVN(String priceVN) {
        this.priceVN = priceVN;
    }


}