package hello.hellospring.service;

import hello.hellospring.domain.Member;
<<<<<<< HEAD:dldusgh318/hello-spring/src/test/java/hello/hellospring/service/MemberServiceIntergrationTest.java
import hello.hellospring.repository.MemberRepository;
=======
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
>>>>>>> 603252662a71571ea71544fb6fe44e71ee77fe0b:ity0526/IntroductionSpring/src/test/java/hello/hellospring/service/MemberServiceTest.java
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

<<<<<<< HEAD:dldusgh318/hello-spring/src/test/java/hello/hellospring/service/MemberServiceIntergrationTest.java

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
=======
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

//  DI
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
>>>>>>> 603252662a71571ea71544fb6fe44e71ee77fe0b:ity0526/IntroductionSpring/src/test/java/hello/hellospring/service/MemberServiceTest.java

    @Test
    void 회원가입() {
        //given
<<<<<<< HEAD:dldusgh318/hello-spring/src/test/java/hello/hellospring/service/MemberServiceIntergrationTest.java
        Member member=new Member();
        member.setName("spring"); //DB에 있으니 오류 ㅇㅇ 지우고오니 되네여

=======
        Member member = new Member();
        member.setName("hello");
>>>>>>> 603252662a71571ea71544fb6fe44e71ee77fe0b:ity0526/IntroductionSpring/src/test/java/hello/hellospring/service/MemberServiceTest.java
        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
<<<<<<< HEAD:dldusgh318/hello-spring/src/test/java/hello/hellospring/service/MemberServiceIntergrationTest.java

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

=======
        assertThat(e.getMessage()).isEqualTo(("이미 존재하는 회원입니다."));

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo(("이미 존재하는 회원입니다."));
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
>>>>>>> 603252662a71571ea71544fb6fe44e71ee77fe0b:ity0526/IntroductionSpring/src/test/java/hello/hellospring/service/MemberServiceTest.java
    }
}