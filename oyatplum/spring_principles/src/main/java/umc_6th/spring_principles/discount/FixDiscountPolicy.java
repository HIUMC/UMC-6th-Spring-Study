package umc_6th.spring_principles.discount;

import umc_6th.spring_principles.member.Grade;
import umc_6th.spring_principles.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {
       if(member.getGrade() == Grade.VIP) {
           return discountFixAmount;
       } else {
           return 0;
       }
    }
}
