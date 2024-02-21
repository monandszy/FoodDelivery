package code.component.manageOrder.domain.mapper;

import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderEntity;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.OrderPositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {

   @Mapping(target = "menuPositions", source = "menuPositions", ignore = true)
   OrderEntity mapToEntity(Order order);

   Order mapFromEntity(OrderEntity orderEntity);

   @Mapping(target = "order", source = "order", ignore = true)
   OrderPositionEntity mapToEntity(OrderPosition orderPosition);

   OrderPosition mapFromEntity(OrderPositionEntity orderPositionEntity);
}