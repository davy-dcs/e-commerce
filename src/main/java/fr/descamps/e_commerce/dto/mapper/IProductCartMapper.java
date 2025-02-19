package fr.descamps.e_commerce.dto.mapper;

import fr.descamps.e_commerce.domain.ProductCart;
import fr.descamps.e_commerce.dto.ProductCartRequest;
import fr.descamps.e_commerce.dto.ProductCartResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IProductCartMapper {
    IProductCartMapper INSTANCE = Mappers.getMapper(IProductCartMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductCartResponse update(ProductCartRequest productCartRequest, @MappingTarget ProductCart productCart);
}
