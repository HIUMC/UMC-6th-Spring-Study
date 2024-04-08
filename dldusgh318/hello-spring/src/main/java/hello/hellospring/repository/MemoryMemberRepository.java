package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.*;
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store=new HashMap<>();
    private static long sequence=0L;

    @Override
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id){
        return Optional.ofNullable(store.get(id)); //null일 경우까지 고려
    }

    @Override
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name){
        return store.values().stream()
                .filter(member->member.getName().equals(name)) //name변수랑 같은지 확인
                .findAny(); //결과가 Optional로 반영됨
    }

    public void clearStore(){
        store.clear();
    }
}
