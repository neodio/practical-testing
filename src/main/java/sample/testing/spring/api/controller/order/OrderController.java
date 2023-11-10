package sample.testing.spring.api.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.testing.spring.api.controller.order.request.OrderCreateRequest;
import sample.testing.spring.api.service.order.OrderService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public void createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        LocalDateTime registeredDateTime = LocalDateTime.now();
        orderService.createOrder(orderCreateRequest, registeredDateTime);
    }
}
