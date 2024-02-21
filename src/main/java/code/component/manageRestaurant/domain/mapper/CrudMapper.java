package code.component.manageRestaurant.domain.mapper;

import code.component.manageRestaurant.domain.MenuDTO;
import code.component.manageRestaurant.domain.MenuEntity;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.MenuPositionEntity;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.domain.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CrudMapper {

   @Mapping(target = "menus", ignore = true)
   RestaurantEntity mapToEntity(RestaurantDTO restaurantDTO);

   RestaurantDTO mapToDTO(RestaurantEntity restaurantEntity);

   @Mapping(target = "menuPositions", ignore = true)
   @Mapping(target = "restaurant", ignore = true)
   MenuEntity mapToEntity(MenuDTO menuDTO);

   MenuDTO mapToDTO(MenuEntity menuEntity);

   @Mapping(target = "menu", ignore = true)
   MenuPositionEntity mapToEntity(MenuPositionDTO menuPositionDTO);

   MenuPositionDTO mapToDTO(MenuPositionEntity menuPositionEntity);
}