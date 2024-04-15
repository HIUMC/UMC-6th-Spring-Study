package hello.core.member;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component  //memoryMemberRepository
public class MemoryMemberRepository implements MemberRepository {

  // 저장소이므로 Map 사용.
  private static Map<Long, Member> store = new HashMap<>();
  @Override
  public void save(Member member) {
    store.put(member.getId(), member);
  }

  @Override
  public Member findById(Long memberId) {
    // Map을 사용했으므로 get으로 memeber를 받아옴.
    return store.get(memberId);
  }
}
