package code.service.manageRestaurant.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@Value
@Builder
@With
public class Restaurant {

   List<Menu> menus;
   Integer sellerAccountCode;
}