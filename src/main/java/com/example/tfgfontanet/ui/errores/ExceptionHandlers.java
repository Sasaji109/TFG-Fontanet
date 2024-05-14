package com.example.tfgfontanet.ui.errores;

import com.example.tfgfontanet.ui.errores.excepciones.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers  {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleException(NotFoundException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ApiError> handleException(TokenException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler(UsuarioNoActivadoException.class)
    public ResponseEntity<ApiError> handleException(UsuarioNoActivadoException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<ApiError> handleException(MailException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiError);
    }

    @ExceptionHandler(PrivateKeyException.class)
    public ResponseEntity<ApiError> handleException(PrivateKeyException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleException(AuthenticationException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(apiError);
    }
}
