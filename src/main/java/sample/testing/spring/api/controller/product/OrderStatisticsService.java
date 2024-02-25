package sample.testing.spring.api.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.testing.spring.api.service.mail.MailService;
import sample.testing.spring.domain.order.Order;
import sample.testing.spring.domain.order.OrderRepository;
import sample.testing.spring.domain.order.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderStatisticsService {

    private final OrderRepository orderRepository;
    private final MailService mailService;

    public boolean sendOrderStatisticsMail(LocalDate orderDate, String email) {
        List<Order> orders = orderRepository.findOrdersBy(
                orderDate.atStartOfDay(),
                orderDate.plusDays(1).atStartOfDay(),
                OrderStatus.PAYMENT_COMPLETED
        );

        // 총 매출 합계를 계산하고
        int totalAmount = orders.stream()
                .mapToInt(Order::getTotalPrice)
                .sum();

        // 메일 전송
        boolean result = mailService.sendMail("no-reply@cafeciosk.com",
                email,
                String.format("[매출통계] %s",orderDate),
                String.format("총 매출 합계는 %s원입니다.", totalAmount)
                );

        if (!result) {
            throw new IllegalArgumentException("매출 통계 메일 전송에 실패했습니다.");
        }

        return true;
    }
}
