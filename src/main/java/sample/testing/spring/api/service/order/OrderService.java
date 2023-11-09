package sample.testing.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.testing.spring.api.controller.order.request.OrderCreateRequest;
import sample.testing.spring.api.service.order.response.OrderResponse;

@Service
@RequiredArgsConstructor
public class OrderService {

    public OrderResponse createOrder(OrderCreateRequest orderCreateRequest) {

    }
}
