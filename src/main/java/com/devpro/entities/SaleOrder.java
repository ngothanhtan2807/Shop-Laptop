package com.devpro.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "tbl_saleorder")
@Getter
@Setter
public class SaleOrder extends BaseEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "total", precision = 13, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "seo")
    private String seo;

    @Transient
    private String totalVN;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "saleOrder"/* tên property category trong class product */
            , fetch = FetchType.LAZY)
    private List<SaleOrderProducts> saleOrderProducts = new ArrayList<SaleOrderProducts>();

    public void addSaleOrderProducts(SaleOrderProducts saleOrderProduct) {
        saleOrderProduct.setSaleOrder(this);
        saleOrderProducts.add(saleOrderProduct);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public String getTotalVN() {
        Locale locale = new Locale("vi", "VN");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        return fmt.format(getTotal());
    }
}
