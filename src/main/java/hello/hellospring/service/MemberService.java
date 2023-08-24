package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // MemberRepository 직접 만드는게 아닌 외부에서 넣어주는 것
    // 내가 직접 new 하지 않음
    // 이게 바로 di (외부에서 넣어줌)
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /*
     *  회원 가입
     * */
    public Long join(Member member) {
        // 같은 이름이 있는 중복회원은 안됨
        // Optional 바로 반환하는게 좋지는 않다
        // Optional<Member> result = memberRepository.findByName(member.getName());
        // ifPresent 값이 있으면!!
        validateDuplicatedMember(member);
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent((m) -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
