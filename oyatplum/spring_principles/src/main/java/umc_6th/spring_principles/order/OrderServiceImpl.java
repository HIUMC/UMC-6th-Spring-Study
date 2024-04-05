package umc_6th.spring_principles.order;

import umc_6th.spring_principles.discount.DiscountPolicy;
import umc_6th.spring_principles.discount.FixDiscountPolicy;
import umc_6th.spring_principles.discount.RateDiscountPolicy;
import umc_6th.spring_principles.member.Member;
import umc_6th.spring_principles.member.MemberRepository;
import umc_6th.spring_principles.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //단일 책임 원칙

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
