package sample.testing.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.testing.spring.api.service.product.response.ProductResponse;
import sample.testing.spring.domain.product.Product;
import sample.testing.spring.domain.product.ProductRepository;
import sample.testing.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(product -> ProductResponse.of(product))
                .collect(Collectors.toList());
    }
}
