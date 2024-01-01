package com.dembasiby.payment.services;

import com.dembasiby.payment.services.paymentGateway.PaymentGateway;
import com.dembasiby.payment.services.paymentGateway.StripePaymentGateway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor @Getter @Setter
@Component
public class PaymentGatewayChooserStrategy {
    private PaymentGateway paymentGateway;
    private StripePaymentGateway stripePaymentGateway;
}
