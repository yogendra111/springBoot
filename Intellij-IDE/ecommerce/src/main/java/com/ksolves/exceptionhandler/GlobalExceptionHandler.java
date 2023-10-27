package com.ksolves.exceptionhandler;

import com.ksolves.payloads.ProductErrorResponse;
import com.ksolves.payloads.StoreErrorResponse;
import com.ksolves.payloads.UserErrorResponse;
import org.hibernate.HibernateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity handleProductNotFoundException(Exception ex){
//        System.out.println("ProductNotFound");
        ProductErrorResponse exceptionDetails = new ProductErrorResponse(12L,ex.getMessage());
        return new ResponseEntity<ProductErrorResponse>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errorMap.put(error.getField(),error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errorMap);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserErrorResponse> handleUserNotFoundException(Exception ex){
        return ResponseEntity.badRequest().body(new UserErrorResponse("", ex.getMessage()));
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<UserErrorResponse> handleUserAlreadyExistsException(Exception ex){
        return ResponseEntity.badRequest().body(new UserErrorResponse("", ex.getMessage()));
    }
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<UserErrorResponse> handleUserLockedException(Exception ex){
        return ResponseEntity.badRequest().body(new UserErrorResponse("Security Exception", ex.getMessage()));
    }
    @ExceptionHandler(StoreException.class)
    public ResponseEntity<StoreErrorResponse> handleStoreException(Exception ex){
        return ResponseEntity.badRequest().body(new StoreErrorResponse("Store Exception", ex.getMessage()));
    }
//    @ExceptionHandler(HibernateException.class)
//    public ResponseEntity<UserErrorResponse> handleHibernateException(Exception ex){
//        return ResponseEntity.badRequest().body(new UserErrorResponse("Hibernate Exception", ex.getMessage()));
//    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserErrorResponse> handleException(Exception ex){
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(new UserErrorResponse("exception", ex.getMessage()));
    }

}
