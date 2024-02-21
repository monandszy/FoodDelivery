package code.component.manageOrder.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = {})
@ToString(of = {})
public class Order {

   Integer id;
   Integer clientCode;
   List<OrderPosition> menuPositions;

}