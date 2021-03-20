package com.hatanaka.ecommerce.checkout.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class CheckoutCreatedEvent {

	private final String message;
}
