package code.service.manageRestaurant.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@Value
@Builder
@With
public class MenuPosition {

   BigDecimal price;
}