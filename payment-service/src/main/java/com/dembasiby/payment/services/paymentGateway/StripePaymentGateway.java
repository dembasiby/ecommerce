package com.dembasiby.payment.services.paymentGateway;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {
    @Override
    public String generatePaymentLink() {
        return "String payment link";
    }
}
