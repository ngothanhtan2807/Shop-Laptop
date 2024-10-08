package com.devpro.services;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.devpro.entities.Cart;
import com.devpro.entities.CartItem;
import com.devpro.entities.SaleOrder;
import com.devpro.entities.SaleOrderProducts;
import com.devpro.repositories.ProductRepo;
import com.devpro.repositories.SaleOrderProductsRepo;
import com.devpro.repositories.SaleOrderRepo;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaleOrderService {
    private final ProductRepo productRepo;
    private final EntityManager entityManager;
    private final SaleOrderRepo saleOrderRepo;
    private final SaleOrderProductsRepo saleOrderProductsRepo;

    public List<SaleOrderProducts> findOrderProductByOrderId(int id) {
        String sql = "select * from tbl_saleorder_products where saleorder_id = " + id;
        Query query = entityManager.createNativeQuery(sql, SaleOrderProducts.class);
        return query.getResultList();
    }

    public SaleOrder findSaleOrderById(int id) {

        String sql = "select * from tbl_saleorder where id = " + id;
        Query query = entityManager.createNativeQuery(sql, SaleOrder.class);
        return (SaleOrder) query.getSingleResult();
    }

    public SaleOrder findSaleOrderByCode(String code) {

        String sql = "select * from tbl_saleorder where code = '" + code + "'";
        Query query = entityManager.createNativeQuery(sql, SaleOrder.class);
        return (SaleOrder) query.getSingleResult();
    }

    public List<SaleOrderProducts> findSaleOrderProductByCode(String code) {

        String sql = "select * from tbl_saleorder_products where saleorder_id = (select id from tbl_saleorder where code='" + code + "')";
        Query query = entityManager.createNativeQuery(sql, SaleOrderProducts.class);
        return query.getResultList();
    }

    public List<SaleOrder> findSaleOrderByUserId(int id) {

        String sql = "select * from tbl_saleorder where user_id = " + id;
        Query query = entityManager.createNativeQuery(sql, SaleOrder.class);
        return query.getResultList();
    }

    @Transactional(rollbackOn = Exception.class)
    public void saveSaleOrder(SaleOrder saleOrder) {

        try {
            saleOrder.setStatus(false);
            saleOrderRepo.save(saleOrder);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    // gửi email khi đặt hàng xong
	/*@Transactional(rollbackOn = Exception.class)
	public void sendEmail(SaleOrder saleOrder, List<CartItem> cartItems)
			throws UnsupportedEncodingException, MessagingException {

		String subject = "Thông báo xác nhận đơn hàng";
		String senderName = "Shop ABC";
		String mailContent = "<p> Dear " + saleOrder.getCustomerName() + "</p>";
		mailContent += "<h2> Thông tin đơn hàng " + saleOrder.getCode() + "</h2>";

		for (CartItem cartItem : cartItems) {
			mailContent += "<p> " + cartItem.getProductName() + " X " + cartItem.getQuantity()
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + cartItem.getUnitPriceVN()
					+ "</p>";
		}
		mailContent += "<h4> Tổng Cộng:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ saleOrder.getTotalVN() + "</h4>";
		mailContent += "<p>-------------------------------------------------------------</p>";
		mailContent += "<h2> Thông tin khách hàng </h2>";
		mailContent += "<p> Tên Khách Hàng: " + saleOrder.getCustomerName() + "</p>";
		mailContent += "<p> Số điện thoại liên lạc: " + saleOrder.getCustomerPhone() + "</p>";
		mailContent += "<p> Địa chỉ: " + saleOrder.getCustomerAddress() + "</p>";
		mailContent += "<p>Cám ơn bạn đã mua hàng!</p>";
		mailContent += "<p>Shop ABC</p>";

//		 Properties props = new Properties(); 
//		 props.put("mail.smtp.starttls.enable", "true"); 
//		MimeMessage message = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message);
//		helper.setFrom("ngothanhtan2608@gmail.com", senderName);
//		helper.setTo(saleOrder.getCustomerEmail());
//		helper.setSubject(subject);
//		helper.setText(mailContent, true);
//
//		mailSender.send(message);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MailSender mailSender = (MailSender) context.getBean("mailSender2");
		System.out.println("Sending text...");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ngothanhtan2608@gmail.com");
		message.setTo(saleOrder.getCustomerEmail());
		message.setSubject(subject);
		message.setText(mailContent);
		// sending message
		mailSender.send(message);
//	    System.out.println("Sending text done!");
		context.close();
	}
*/
    @Transactional(rollbackOn = Exception.class)
    public void saveOrderProduct(String customerAddress, String customerName, String customerPhone,
                                 String customerEmail, HttpSession httpSession) throws Exception {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setCode("ORDER-" + System.currentTimeMillis());
        saleOrder.setSeo("ORDER-" + System.currentTimeMillis());
        saleOrder.setCustomerName(customerName);
        saleOrder.setCustomerAddress(customerAddress);
        saleOrder.setCustomerPhone(customerPhone);
        saleOrder.setCustomerEmail(customerEmail);
        saleOrder.setStatus(true);

        Cart cart = (Cart) httpSession.getAttribute("GIO_HANG");
        List<CartItem> cartItems = cart.getCartItems();

        BigDecimal sum = new BigDecimal(0);

        for (CartItem item : cartItems) {
            SaleOrderProducts saleOrderProducts = new SaleOrderProducts();
            saleOrderProducts.setProduct(productRepo.getOne(item.getProductId()));
            saleOrderProducts.setQuantity(item.getQuantity());
            saleOrder.addSaleOrderProducts(saleOrderProducts);
            saleOrderProducts.setStatus(true);
            for (int i = 1; i <= item.getQuantity(); i++) {
                sum = sum.add(saleOrderProducts.getProduct().getPrice());
            }

        }
        saleOrder.setCreatedDate(java.time.LocalDateTime.now());
        saleOrder.setTotal(sum);
        saleOrderRepo.save(saleOrder);
        //	sendEmail(saleOrder, cartItems);
        httpSession.setAttribute("SL_SP_GIO_HANG", 0);
        httpSession.setAttribute("GIO_HANG", null);
    }
}
