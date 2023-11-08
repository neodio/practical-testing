package sample.testing.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    @Test
    void findAllBySellingStatusIn() {
        //given
        Product product1 = Product.builder()
                            .productNumber("001")
                            .type(ProductType.HANDMADE)
                            .sellingStatus(ProductSellingStatus.SELLING)
                            .name("아메리카노")
                            .price(4000)
                            .build();
        //when

        //then

    }
}