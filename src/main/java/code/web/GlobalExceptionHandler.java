package code.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//   @ExceptionHandler(Throwable.class)
//   public ModelAndView handleException(Exception ex) {
//      String message = String.format("Exception [%s] occurred: [%s]", ex.getClass(), ex.getMessage());
//      log.error(message, ex);
//      ModelAndView modelView = new ModelAndView("error");
//      modelView.addObject("exceptionMessage", message);
//      return modelView;
//   }

//   @ExceptionHandler(NotImplementedException.class)
//   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//   public ModelAndView handleException(NotImplementedException ex) {
//      String message = String.format("NotImplementedException occurred: [%s]", ex.getMessage());
//      log.error(message, ex);
//      ModelAndView modelView = new ModelAndView("redirect:error");
//      modelView.addObject("exceptionMessage", message);
//      return modelView;
//   }

//   @ExceptionHandler(BindException.class)
//   @ResponseStatus(HttpStatus.BAD_REQUEST)
//   public ModelAndView handleBindException(BindException ex) {
//      String message = String.format("Bad request for field: [%s], wrong value: [%s]",
//          Optional.ofNullable(ex.getFieldError()).map(FieldError::getField).orElse(null),
//          Optional.ofNullable(ex.getFieldError()).map(FieldError::getRejectedValue).orElse(null));
//      log.error(message, ex);
//      ModelAndView modelView = new ModelAndView("error");
//      modelView.addObject("exceptionMessage", message);
//      return modelView;
//   }

}