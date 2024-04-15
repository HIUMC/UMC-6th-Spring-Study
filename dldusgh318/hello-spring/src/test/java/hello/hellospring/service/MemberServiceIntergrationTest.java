package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest     //스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional
//덕분에 AfterEach 필요 없음
class MemberServiceIntergrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    //굳이 두개를 ? 다른 두개의 repository를 이용중.. 비효율적?

/*
    @AfterEach
    public void afterEach(){
        //어쩌고... 하기 귀찮 ㅇㅇ
    }*/
    //그럼 롤백을 해보면????? ==>Transactional
    //일케하면 DB에 데이터가 남지않음 ==> 즉 다음 테스트가 계속 가능함 ㅇㅇ

    @Test
    void 회원가입() {
        //given
        Member member=new Member();
        member.setName("spring"); //DB에 있으니 오류 ㅇㅇ 지우고오니 되네여

        //when
        Long saveId=memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}