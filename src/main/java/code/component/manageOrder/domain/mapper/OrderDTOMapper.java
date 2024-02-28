package code.component.manageOrder.domain.mapper;

import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDTOMapper {

   @Mapping(target = "addressOutput", source = "address", qualifiedByName = "addressMapping")
   @Mapping(target = "restaurantId", source = "restaurant", qualifiedByName = "restaurantMapping")
   @Mapping(target = "timeOfOrder", source = "timeOfOrder", qualifiedByName = "timeToStringMapping")
   OrderDTO mapToDTO(Order order);

   @Mapping(target = "orderPositions", ignore = true)
   @Mapping(target = "client", ignore = true)
   @Mapping(target = "timeOfOrder", source = "timeOfOrder", qualifiedByName = "stringToTimeMapping")
   Order mapFromDTO(OrderDTO orderDTO);

   @Mapping(target = "orderId" , source = "order", qualifiedByName = "orderMapping")
   @Mapping(target = "menuPositionId" , source = "menuPosition", qualifiedByName = "menuPositionMapping")
   OrderPositionDTO mapToDTO(OrderPosition orderPosition);

   @Mapping(target = "order", ignore = true)
   @Mapping(target = "menuPosition", ignore = true)
   OrderPosition mapFromDTO(OrderPositionDTO orderPositionDTO);

   @Named("addressMapping")
   default String addressMapping(Address address) {
      if (Objects.isNull(address)) return null;
      return address.toString();
   }

   @Named("restaurantMapping")
   default Integer restaurantMapping(Restaurant restaurant) {
      if (Objects.isNull(restaurant)) return null;
      return restaurant.getId();
   }

   @Named("orderMapping")
   default Integer orderIdMapping(Order order) {
      if (Objects.isNull(order)) return null;
      return order.getId();
   }

   @Named("menuPositionMapping")
   default Integer menuPositionMapping(MenuPosition menuPosition) {
      if (Objects.isNull(menuPosition)) return null;
      return menuPosition.getId();
   }

   @Named("timeToStringMapping")
   default String timeToStringMapping(OffsetDateTime offsetDateTime) {
      return offsetDateTime.toString();
   }

   @Named("stringToTimeMapping")
   default OffsetDateTime stringToTimeMapping(String time) {
      return OffsetDateTime.parse(time);
   }

   List<OrderPosition> mapOPFromDTOList(List<OrderPositionDTO> orderPositions);
   List<OrderPositionDTO> mapOPToDTOList(List<OrderPosition> orderPositions);

   List<Order> mapOFromDTOList(List<OrderDTO> orderPositions);
   List<OrderDTO> mapOToDTOList(List<Order> orderPositions);
}