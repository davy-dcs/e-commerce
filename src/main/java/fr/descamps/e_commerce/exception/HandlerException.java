package fr.descamps.e_commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.relation.RoleNotFoundException;

@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler(value = {ProductNotFoundException.class, ProductCartNotFoundException.class, CartNotFoundException.class, RoleNotFoundException.class, UsernameNotFoundException.class, UserNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("Erreur serveur interne : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
