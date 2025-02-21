package fr.descamps.e_commerce.dto.mapper;

import fr.descamps.e_commerce.domain.Cart;
import fr.descamps.e_commerce.dto.CartRequest;
import fr.descamps.e_commerce.dto.CartResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICartMapper {
    ICartMapper INSTANCE = Mappers.getMapper(ICartMapper.class);

    @Mapping(source = "productCarts", target = "productCarts")
    CartResponse cartToCartResponse(Cart cart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCart(CartRequest cartRequest, @MappingTarget Cart cart);

}
