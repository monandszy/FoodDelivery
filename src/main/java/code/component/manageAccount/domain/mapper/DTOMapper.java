package code.component.manageAccount.domain.mapper;

import code.component.manageAccount.domain.client.Client;
import code.component.manageAccount.domain.client.ClientDTO;
import code.component.manageAccount.domain.seller.Seller;
import code.component.manageAccount.domain.seller.SellerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DTOMapper {


   ClientDTO mapToDTO(Client client);

   Client mapFromDTO(ClientDTO clientDTO);

   SellerDTO mapToDTO(Seller seller);

   Seller mapFromDTO(SellerDTO sellerDTO);
}