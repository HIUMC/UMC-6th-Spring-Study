package umc_6th.spring_principles.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
