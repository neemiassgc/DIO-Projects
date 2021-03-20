package com.hatanaka.ecommerce.checkout.controller;

import com.hatanaka.ecommerce.checkout.entity.CheckoutCreatedEvent;
import com.hatanaka.ecommerce.checkout.streaming.CheckoutCreatedSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutResource {

    private final CheckoutCreatedSource checkoutCreatedSource;

    @PostMapping("/")
    public ResponseEntity<Void> create() {
        CheckoutCreatedEvent checkoutCreatedEvent =
            CheckoutCreatedEvent.builder().message("Event created").build();
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());
        return ResponseEntity.ok().build();
    }
}
