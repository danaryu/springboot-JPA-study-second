package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    // @Autowired EntityManager em;

    @Test
    // @Rollback(false) @Transactional Annotation이 Test에 있으면, 기본적으로 Rollback이 되어 insert 쿼리가 보이지 않음! Rollback Transaction for Test
    // 영속성 context가 flush를 하지 않음 (-> Rollback시에 insert (X))
    @Rollback(false)
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("dana");

        // when
        Long savedId = memberService.join(member);

        // then
        // em.flush(); // 영속성 CONTEXT FLUSH
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("heejae");

        Member member2 = new Member();
        member2.setName("heejae");

        // when
        memberService.join(member1);
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

//        @Test(expected = 예외.class) 로 간단히 바꿀 수 있다. (Junit4)
//        try {
//            memberService.join(member2); // 예외 발생!
//        } catch (IllegalStateException e) {
//            return;
//        }

        // then
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
        // fail("예외가 발생해야 한다."); // 여기까지는 발생되어서는 안된다. 위의 예외발생 부분에서 예외가 던져져야..
    }

}