package code.component.manageRestaurant.domain.mapper;

import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuDTO;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.RestaurantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DTOMapper {

   RestaurantDTO mapToDTO(Restaurant restaurant);

   @Mapping(target = "menus", ignore = true)
   Restaurant mapFromDTO(RestaurantDTO restaurantDTO);

   MenuDTO mapToDTO(Menu menu);

   @Mapping(target = "menuPositions", ignore = true)
   @Mapping(target = "restaurant", ignore = true)
   Menu mapFromDTO(MenuDTO menuDTO);

   MenuPositionDTO mapToDTO(MenuPosition menuPosition);

   @Mapping(target = "menu", ignore = true)
   MenuPosition mapFromDTO(MenuPositionDTO menuPositionDTO);
}