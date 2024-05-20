package umc_6th.spring_principles.order;

<<<<<<< HEAD:do-dop/스프링_기본/core/src/main/java/hello/core/order/OrderServiceImpl.java
import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import umc_6th.spring_principles.discount.DiscountPolicy;
import umc_6th.spring_principles.discount.FixDiscountPolicy;
import umc_6th.spring_principles.discount.RateDiscountPolicy;
import umc_6th.spring_principles.member.Member;
import umc_6th.spring_principles.member.MemberRepository;
import umc_6th.spring_principles.member.MemoryMemberRepository;
>>>>>>> main:oyatplum/spring_principles/src/main/java/umc_6th/spring_principles/order/OrderServiceImpl.java

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
<<<<<<< HEAD:do-dop/스프링_기본/core/src/main/java/hello/core/order/OrderServiceImpl.java
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
=======
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
>>>>>>> main:oyatplum/spring_principles/src/main/java/umc_6th/spring_principles/order/OrderServiceImpl.java
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //단일 책임 원칙

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
