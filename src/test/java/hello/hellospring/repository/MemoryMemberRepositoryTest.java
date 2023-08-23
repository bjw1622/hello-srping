package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemberRepositroy repositroy = new MemoryMemberRepository();

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repositroy.save(member);
        // optional에서 get으로 꺼내기
        Member result = repositroy.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

}