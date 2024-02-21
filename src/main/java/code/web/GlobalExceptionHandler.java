package code.web;

import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(Exception.class)
   public ModelAndView handleException(Exception ex) {
      return new ModelAndView("error");
   }

   @ExceptionHandler(BindException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ModelAndView handleBindException(BindException ex) {
      return new ModelAndView("error");
   }
}