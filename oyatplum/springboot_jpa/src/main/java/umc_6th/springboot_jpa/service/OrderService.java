package umc_6th.springboot_jpa.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc_6th.springboot_jpa.domain.Delivery;
import umc_6th.springboot_jpa.domain.Member;
import umc_6th.springboot_jpa.domain.Order;
import umc_6th.springboot_jpa.domain.OrderItem;
import umc_6th.springboot_jpa.domain.item.Item;
import umc_6th.springboot_jpa.repository.ItemRepository;
import umc_6th.springboot_jpa.repository.MemberRepository;
import umc_6th.springboot_jpa.repository.OrderRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //entitiy 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();

    }
    //주문 취소
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
