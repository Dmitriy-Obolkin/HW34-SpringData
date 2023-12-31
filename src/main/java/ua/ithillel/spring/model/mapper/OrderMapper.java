package ua.ithillel.spring.model.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ua.ithillel.spring.database.entity.Order;
import ua.ithillel.spring.database.entity.OrderProduct;
import ua.ithillel.spring.model.dto.OrderDTO;
import ua.ithillel.spring.model.dto.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = ProductMapper.class)
public abstract class OrderMapper {

    @Autowired
    private ProductMapper productMapper;

    @Mapping(
            source = "order.orderProducts",
            target = "productDTOs",
            qualifiedByName = "orderProductsToProductDTOs"
    )
    public abstract OrderDTO orderToOrderDTO(Order order);

    @Mapping(
            source = "orderDTO.productDTOs",
            target = "orderProducts",
            qualifiedByName = "productDTOsToOrderProducts"
    )
    public abstract Order orderDTOToOrder(OrderDTO orderDTO);

    @Named("orderProductsToProductDTOs")
    public List<ProductDTO> orderProductsToProductDTOs(List<OrderProduct> orderProducts) {
        return orderProducts.stream()
                .map(OrderProduct::getProduct)
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    @Named("productDTOsToOrderProducts")
    List<OrderProduct> productDTOsToOrderProducts(List<ProductDTO> productDTOs) {
        return productDTOs.stream().map(productDTO -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(productMapper.productDTOToProduct(productDTO));
            return orderProduct;
        }).collect(Collectors.toList());
    }
}
