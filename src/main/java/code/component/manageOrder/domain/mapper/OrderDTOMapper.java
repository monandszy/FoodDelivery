package code.component.manageOrder.domain.mapper;

import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.OrderPositionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDTOMapper {

   OrderDTO mapToDTO(Order order);

   @Mapping(target = "menuPositions", ignore = true)
   Order mapFromDTO(OrderDTO orderDTO);

   OrderPositionDTO mapToDTO(OrderPosition orderPosition);

   OrderPosition mapFromDTO(OrderPositionDTO orderPositionDTO);

}