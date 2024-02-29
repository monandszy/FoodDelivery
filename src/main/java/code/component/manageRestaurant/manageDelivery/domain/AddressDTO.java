package code.component.manageRestaurant.manageDelivery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AddressDTO {

   private Integer id;
   private String city;
   private String postalCode;
   private String ipAddress;
   private BigDecimal latitude;
   private BigDecimal longitude;
}