package jpabook.jpashop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {
/*

    @Autowired
    MemberRepository memberRepository;

    @Test
    // Transactional이 Test case에 있으면, Test가 끝나고 Rollback한다. Rolled back transaction for test
    @Rollback(false) // 이 annotaion을 추가하면 Rollback을 하지 않고, Commit한다.
    @Transactional
    public void testMember() throws Exception {

        //given
        Member member = new Member();
        member.setUsername("danadot");

        // when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        // then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);

        // 영속성 Context 에서 식별자가 같으면, 같은 객체 (1차 캐쉬)
        System.out.println("findMember == member:  " + (findMember == member));

    }
*/
}