package fr.descamps.e_commerce.dto.mapper;

import fr.descamps.e_commerce.domain.ProductCart;
import fr.descamps.e_commerce.dto.ProductCartQuantityRequest;
import fr.descamps.e_commerce.dto.ProductCartResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IProductCartMapper {
    IProductCartMapper INSTANCE = Mappers.getMapper(IProductCartMapper.class);

    @Mapping(source = "product", target = "product")
    ProductCartResponse productToProductCartResponse(ProductCart productCart);

    List<ProductCartResponse> productsToProductCartResponseList(List<ProductCart> productCarts);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductCart(ProductCartQuantityRequest productCartQuantityRequest, @MappingTarget ProductCart productCart);
}
