package code.api;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiGlobalExceptionHandler extends ResponseEntityExceptionHandler {

   private static final Map<Class<?>, HttpStatus> EXCEPTION_STATUS = Map.of(
       ConstraintViolationException.class, HttpStatus.BAD_REQUEST,
       EntityNotFoundException.class, HttpStatus.NOT_FOUND,
       NoSuchElementException.class, HttpStatus.NOT_FOUND
   );

   @Override
   protected ResponseEntity<Object> handleExceptionInternal(
       @NonNull Exception ex,
       Object body,
       @NonNull HttpHeaders headers,
       @NonNull HttpStatusCode statusCode,
       @NonNull WebRequest request
   ) {
      final String errorId = UUID.randomUUID().toString();
      log.error("Rest Api Exception: [{}] [{}] [{}]", errorId, ex.getMessage(), statusCode);
      return super.handleExceptionInternal(
          ex, errorId, headers, statusCode, request
      );
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<Object> handle(Exception ex){
      return doHandle(ex, EXCEPTION_STATUS.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR));
   }

   private ResponseEntity<Object> doHandle(Exception ex, HttpStatus httpStatus) {
      final String errorId = UUID.randomUUID().toString();
      log.error("Rest Api Exception: [{}] [{}] [{}]", errorId, ex.getMessage(), httpStatus);
      return ResponseEntity
          .status(httpStatus)
          .contentType(MediaType.APPLICATION_JSON)
          .body(errorId);
   }
}