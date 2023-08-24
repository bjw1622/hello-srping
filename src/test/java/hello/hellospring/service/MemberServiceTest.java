package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 테스트를 실행하기 전
    // 같은 memberRepository 쓰기 위해서
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    // 메서드가 하나씩 끝나고나서 실행
    // 데이터들이 겹치지 않기 위해서 메서드 실행 후 실행해줘서 비우기
    // 각 테스트 메서드가 실행된 후에 필요한 정리 작업을 수행 할 수 있는 것
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    // 테스트의 경우 과감하게 한글로 사용해도 ok
    // 테스트는 예외의 flow 중요!
    @Test
    void 회원가입() {
        // given
        // 이 데이터를 기반으로 하는구나
        Member member = new Member();
        member.setName("백재원");

        // when
        Long saveId = memberService.join(member);

        // then
        // 검증하는 곳이구나
        // repository 값을 가져온다고 생각
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

        afterEach();
    }

    @Test
    public void 중복_회원_예약() {
        // given
        Member member = new Member();
        member.setName("spring");
        Member member1 = new Member();
        member1.setName("spring");

        // when
        memberService.join(member);
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        //        try{
//            memberService.join(member1);
//            fail();
//        }
//        catch(IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // then
        afterEach();

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}