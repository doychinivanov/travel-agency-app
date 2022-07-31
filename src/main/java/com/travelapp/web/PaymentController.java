package com.travelapp.web;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.travelapp.models.dto.CreatePayment;
import com.travelapp.models.dto.CreatePaymentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.aspectj.runtime.internal.Conversions.longValue;

@RestController
public class PaymentController {

    @PostMapping("/create-payment-intent")
    public ResponseEntity<CreatePaymentResponse> createPaymentIntent(@RequestBody CreatePayment createPayment) throws StripeException {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams
                        .builder()
                        .setAmount(longValue(createPayment.getPrice().multiply(BigDecimal.valueOf(100L))))
                        .setCurrency("eur")
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return ResponseEntity.ok(new CreatePaymentResponse(paymentIntent.getClientSecret()));
    }
}
