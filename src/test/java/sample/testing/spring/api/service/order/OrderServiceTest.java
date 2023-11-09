package sample.testing.spring.api.service.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sample.testing.spring.api.controller.order.request.OrderCreateRequest;
import sample.testing.spring.api.service.order.response.OrderResponse;
import sample.testing.spring.domain.product.Product;
import sample.testing.spring.domain.product.ProductRepository;
import sample.testing.spring.domain.product.ProductType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sample.testing.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.testing.spring.domain.product.ProductType.HANDMADE;

@DataJpaTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    void createOrder() {
        //given
        Product product1 = createProduct(HANDMADE, "001", 1000);
        Product product2 = createProduct(HANDMADE, "002", 3000);
        Product product3 = createProduct(HANDMADE, "003", 5000);

        productRepository.saveAll(List.of(product1, product2, product3));

        OrderCreateRequest request = OrderCreateRequest.builder()
                                                    .productNumbers(List.of("001", "002"))
                                                    .build();

        //when
        OrderResponse orderResponse = orderService.createOrder(request);

        //then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("re")
    }

    private Product createProduct(ProductType productType, String productNumber, int price) {
        return Product.builder()
                .type(productType)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }

}