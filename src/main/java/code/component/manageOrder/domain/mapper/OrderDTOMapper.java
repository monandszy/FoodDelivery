package code.component.manageOrder.domain.mapper;

import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageRestaurant.domain.MenuPosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDTOMapper {

   OrderDTO mapToDTO(Order order);

   @Mapping(target = "menuPositions", ignore = true)
   Order mapFromDTO(OrderDTO orderDTO);

   @Mapping(target = "orderId" , source = "order", qualifiedByName = "orderMapping")
   @Mapping(target = "menuPositionId", source = "menuPosition", qualifiedByName = "menuPositionMapping")
   OrderPositionDTO mapToDTO(OrderPosition orderPosition);

   @Mapping(target = "order", ignore = true)
   @Mapping(target = "menuPosition", ignore = true)
   OrderPosition mapFromDTO(OrderPositionDTO orderPositionDTO);

   @Named("orderMapping")
   default Integer orderIdMapping(Order order) {
      return order.getId();
   }

   @Named("menuPositionMapping")
   default Integer menuPositionMapping(MenuPosition menuPosition) {
      return menuPosition.getId();
   }

}