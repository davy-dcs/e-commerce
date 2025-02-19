package fr.descamps.e_commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class ProductCartNotFoundException extends RuntimeException {
    public ProductCartNotFoundException(String message) {
        super(message);
    }
}
