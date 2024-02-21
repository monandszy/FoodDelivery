package code.component.manageAccount.domain.mapper;

import code.component.manageAccount.domain.client.Client;
import code.component.manageAccount.domain.client.ClientEntity;
import code.component.manageAccount.domain.seller.Seller;
import code.component.manageAccount.domain.seller.SellerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface entityMapper {

   ClientEntity mapToEntity(Client client);

   Client mapFromEntity(ClientEntity clientEntity);

   SellerEntity mapToEntity(Seller Seller);

   Seller mapFromEntity(SellerEntity SellerEntity);
}