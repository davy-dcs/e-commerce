package fr.descamps.e_commerce.dto.mapper;

import fr.descamps.e_commerce.domain.ProductCart;
import fr.descamps.e_commerce.dto.ProductCartRequest;
import fr.descamps.e_commerce.dto.ProductCartResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IProductCartMapper {
    IProductCartMapper INSTANCE = Mappers.getMapper(IProductCartMapper.class);

    @Mapping(source = "product", target = "product")
    ProductCartResponse productToProductCartResponse(ProductCart productCart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductCart(ProductCartRequest productCartRequest, @MappingTarget ProductCart productCart);
}
