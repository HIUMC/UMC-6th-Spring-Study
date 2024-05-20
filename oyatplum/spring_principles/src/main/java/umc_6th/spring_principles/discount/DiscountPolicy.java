package umc_6th.spring_principles.discount;

import umc_6th.spring_principles.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
