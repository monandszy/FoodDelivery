package code.component.manageRestaurant.domain;

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
public class Restaurant {

   Integer id;
   String sellerAccountCode;
   List<Menu> menus;
}