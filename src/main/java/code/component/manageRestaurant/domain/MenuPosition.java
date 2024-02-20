package code.component.manageRestaurant.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@With
@Value
@Builder
@EqualsAndHashCode(of = {})
@ToString(of = {})
public class MenuPosition {

   Integer id;
   BigDecimal price;
}