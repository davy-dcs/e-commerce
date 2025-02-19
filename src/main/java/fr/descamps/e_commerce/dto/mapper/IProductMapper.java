package fr.descamps.e_commerce.dto.mapper;

import fr.descamps.e_commerce.domain.Product;
import fr.descamps.e_commerce.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IProductMapper {
    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);
    ProductResponse productToProductResponse(Product product);
    List<ProductResponse> productsToProductResponseList(List<Product> products);
}
