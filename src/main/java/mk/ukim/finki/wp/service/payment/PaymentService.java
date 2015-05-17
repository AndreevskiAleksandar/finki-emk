package mk.ukim.finki.wp.service.payment;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Payment;
import mk.ukim.finki.wp.model.OrderItem;

import java.util.List;

/**
 * Created by ristes on 6.5.15.
 */
public interface PaymentService {

  public Payment executeCreditCardPayment(Address billingAddress,
                                          CreditCard creditCard,
                                          List<OrderItem> items);
}
