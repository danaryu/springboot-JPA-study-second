package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final이 있는 FIELD만 생성
public class MemberService {

    /**
     * Field 생성자 주입 : private으로 의존성을 주입하기 때문에, 한 번 의존성이 주입되는 경우 Test등 변경/접근이 까다로움
     *    @Autowired
     *    private MemberRepository memberRepository;
     *
     * Setter 주입 : Test등 여러 환경에 따라 의존성 주입이 가능하나, 생성 시점에 Setter를 이용해 다른 인스턴스를 주입할 수 있기 때문에 위험함
     *    @Autowired
     *    public void setMemberRepository(MemberRepository memberRepository) {
     *        this.memberRepository = memberRepository;
     *    }
     *
     * 따라서, 가장 안전한 생성자 주입을 권장한다. (의존성 파악이 쉬움)
     *    생성자가 하나인 경우, Autowired Annotation이 없어도 생성자 주입이 가능함.
     *     & final로 하길 권장. Compile 시점에 체크가 가능하기 때문
     *    @Autowired
     *    public MemberService(MemberRepository memberRepository) {
     *        this.memberRepository = memberRepository;
     *    }
     */

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        // 항상 값이 있다는 것이 보장..? Id를 돌려줘야 어떤 회원이 저장되었는지 확인 가능
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 중복회원이면, Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     *  회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name); // 변경감지 (dirty Checking)
    }
}
