package one.digitalinnovation.beerstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegativeBeersQuantityException extends Exception {

	public NegativeBeersQuantityException (String beerName) {
		super(String.format("Quantity of the beers with name %s has negative value", beerName));
	}

	public NegativeBeersQuantityException (long id) {
		super(String.format("Quantity of the beers with id %d has negative value", id));
	}
}
