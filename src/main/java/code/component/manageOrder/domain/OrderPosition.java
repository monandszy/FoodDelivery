package code.component.manageOrder.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.With;
@With
@Value
@Builder
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class OrderPosition {

   Integer id;
   Order order;
   Integer menuPositionId;
}