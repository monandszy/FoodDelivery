package code.web.exception;

public class DeliveryError extends RuntimeException {

   public DeliveryError() {
   }

   public DeliveryError(String message) {
      super(message);
   }
}