package code.component.manageRestaurant.manageDelivery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("session")
public class AddressDTO {

   private Integer id;
}