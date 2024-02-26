package code.component.manageOrder.domain.mapper;

import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderEntity;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.OrderPositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {

   @Mapping(target = "orderPositions", source = "orderPositions", ignore = true)
   @Mapping(target = "address", source = "address", ignore = true)
   @Mapping(target = "client", source = "client", ignore = true)
   OrderEntity mapToEntity(Order order);

   @Mapping(target = "orderPositions", source = "orderPositions", ignore = true)
   @Mapping(target = "address", source = "address", ignore = true)
   @Mapping(target = "client", source = "client", ignore = true)
   Order mapFromEntity(OrderEntity orderEntity);

   @Mapping(target = "order", source = "order", ignore = true)
   OrderPositionEntity mapToEntity(OrderPosition orderPosition);

   @Mapping(target = "order", source = "order", ignore = true)
   OrderPosition mapFromEntity(OrderPositionEntity orderPositionEntity);


   List<OrderPosition> mapOPFromEntityList(List<OrderPositionEntity> orderPositions);
   List<OrderPositionEntity> mapOPToEntityList(List<OrderPosition> orderPositions);

   List<Order> mapOFromEntityList(List<OrderEntity> orderPositions);
   List<OrderEntity> mapOToEntityList(List<Order> orderPositions);
}