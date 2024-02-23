package code.web;

import code.web.exception.NotImplementedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

   @ExceptionHandler(Throwable.class)
   public String handleException(Exception ex, Model model) {
      String message = String.format("Exception [%s] occurred: [%s]", ex.getClass(), ex.getMessage());
      log.error(message, ex);
      model.addAttribute("errorMessage", message);
      return "error";
   }

   @ExceptionHandler(NotImplementedException.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public String handleException(NotImplementedException ex, Model model) {
      String message = String.format("NotImplementedException occurred: [%s]", ex.getMessage());
      log.error(message, ex);
      model.addAttribute("errorMessage", message);
      return "error";
   }

   @ExceptionHandler(BindException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public String handleBindException(BindException ex, Model model) {
      String message = String.format("Bad request for field: [%s], wrong value: [%s]",
          Optional.ofNullable(ex.getFieldError()).map(FieldError::getField).orElse(null),
          Optional.ofNullable(ex.getFieldError()).map(FieldError::getRejectedValue).orElse(null));
      log.error(message, ex);
      model.addAttribute("errorMessage", message);
      return "error";
   }

}