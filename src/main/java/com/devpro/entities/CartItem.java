package com.devpro.entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Setter
@Getter
public class CartItem {
	private int productId;
	private String productName;
	private int quantity;
	private BigDecimal unitPrice;
	private String unitPriceVN;

    public String getUnitPriceVN() {
        Locale locale = new Locale("vi", "VN");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        unitPriceVN = fmt.format(unitPrice);
        return unitPriceVN;
    }

}
