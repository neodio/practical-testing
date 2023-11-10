package sample.testing.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.testing.spring.api.controller.order.request.OrderCreateRequest;
import sample.testing.spring.api.service.order.response.OrderResponse;
import sample.testing.spring.domain.order.Order;
import sample.testing.spring.domain.order.OrderRepository;
import sample.testing.spring.domain.product.Product;
import sample.testing.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest orderCreateRequest, LocalDateTime registeredDateTime) {
        List<String> productNumbers = orderCreateRequest.getProductNumbers();

        // Product
        List<Product> products = findProductsBy(productNumbers);

        // Order
        Order order = Order.create(products, registeredDateTime);
        Order saveOrder = orderRepository.save(order);

        return OrderResponse.of(saveOrder);
    }

    private List<Product> findProductsBy(List<String> productNumbers) {
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductNumber, p -> p));

        return productNumbers.stream()
                    .map(productMap::get)
                    .collect(Collectors.toList());
    }
}
