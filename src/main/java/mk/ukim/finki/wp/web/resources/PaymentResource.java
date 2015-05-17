package mk.ukim.finki.wp.web.resources;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Payment;
import mk.ukim.finki.wp.model.Category;
import mk.ukim.finki.wp.model.EmailMessage;
import mk.ukim.finki.wp.model.OrderItem;
import mk.ukim.finki.wp.service.CategoryService;
import mk.ukim.finki.wp.service.OrderItemService;
import mk.ukim.finki.wp.service.mail.EmailNotificationService;
import mk.ukim.finki.wp.service.payment.PaymentService;
import mk.ukim.finki.wp.web.CrudResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentResource {

  @Autowired
  private PaymentService paymentService;


  @Autowired
  private EmailNotificationService emailNotificationService;


  @Autowired
  private OrderItemService orderItemService;

  @RequestMapping(value = "/test_pay", method = RequestMethod.GET, produces = "application/json")
  public Payment testPay() {
// ###Address
    // Base Address object used as shipping or billing
    // address in a payment. [Optional]
    Address billingAddress = new Address();
    billingAddress.setCity("Johnstown");
    billingAddress.setCountryCode("US");
    billingAddress.setLine1("52 N Main ST");
    billingAddress.setPostalCode("43210");
    billingAddress.setState("OH");

    // ###CreditCard
    // A resource representing a credit card that can be
    // used to fund a payment.
    CreditCard creditCard = new CreditCard();
    creditCard.setBillingAddress(billingAddress);
    creditCard.setCvv2(111);
    creditCard.setExpireMonth(11);
    creditCard.setExpireYear(2018);
    creditCard.setFirstName("Joe");
    creditCard.setLastName("Shopper");
    creditCard.setNumber("5500005555555559");
    creditCard.setType("mastercard");

    List<OrderItem> items = orderItemService.findByUserToken("4d810a7c-65e9-4db6-8457-c254ac96d127");

    return paymentService.executeCreditCardPayment(billingAddress,
      creditCard,
      items);
  }

  @RequestMapping("send_email_test")
  public void sendEmail() {
    EmailMessage message = EmailMessage.create()
      .subject("test email")
      .to("riste.stojanov@finki.ukim.mk")
      .template("test_email.vm")
      .addToModel("name", "Ристе")
      .addToModel("number", 12345);

    emailNotificationService.sendEmail(message);
  }

}
