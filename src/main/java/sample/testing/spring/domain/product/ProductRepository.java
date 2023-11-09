package sample.testing.spring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * select *
     * from proudt
     * where selling_type in ('SELLING', 'HOLD');
     */
    List<Product> findBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);
}
