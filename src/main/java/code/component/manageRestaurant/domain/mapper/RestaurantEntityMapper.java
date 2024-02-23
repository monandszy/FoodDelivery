package code.component.manageRestaurant.domain.mapper;

import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuEntity;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.MenuPositionEntity;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {
   @Mapping(target = "menus", source = "menus", ignore = true)
   RestaurantEntity mapToEntity(Restaurant restaurant);

   @Mapping(target = "menus", source = "menus", ignore = true)
   Restaurant mapFromEntity(RestaurantEntity restaurantEntity);

//   @Mapping(target = "menuPositions", source = "menuPositions", ignore = true)
   @Mapping(target = "restaurant", source = "restaurant", ignore = true)
   MenuEntity mapToEntity(Menu menu);

//   @Mapping(target = "menuPositions", source = "menuPositions", ignore = true)
   @Mapping(target = "restaurant", source = "restaurant", ignore = true)
   Menu mapFromEntity(MenuEntity menuEntity);

   @Mapping(target = "menu", source = "menu", ignore = true)
   MenuPositionEntity mapToEntity(MenuPosition menuPosition);

   @Mapping(target = "menu", source = "menu", ignore = true)
   MenuPosition mapFromEntity(MenuPositionEntity menuPositionEntity);
}