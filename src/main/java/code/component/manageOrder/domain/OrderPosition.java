package code.component.manageOrder.domain;

import code.component.manageRestaurant.domain.MenuPosition;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.With;
@With
@Value
@Builder
@EqualsAndHashCode(of = {})
@ToString(of = {})
public class OrderPosition {

   Integer id;
   Order order;
   MenuPosition menuPosition;
}