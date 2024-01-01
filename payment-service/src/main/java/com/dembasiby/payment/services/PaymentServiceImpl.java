package com.dembasiby.payment.services;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String initiatePayment(String orderId) {
        // Order order = orderService.getOrderDetails(orderId);
        // Long amount = order.getAmount();

        // double amount = 10.00;  // store number * 100 as Long in database
        return "Payment initiated for order id: " + orderId;
    }
}
