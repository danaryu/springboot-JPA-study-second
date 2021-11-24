package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    /*
        Spring이 생성한 Entity Manager 를 주입함 (Spring이 알아서 해결^0^)
        @PersistenceUnit
        private EntityManagerFactory; 이런식으로, factory도 주입받을 수 있음!

        @PersistenceContext
        private EntityManager em;
        -> SpringBoot DATA JPA에서는, 해당 Annotation을 달아주지 않아도, 아래와 같이 Spring 의존성 주입의 방식으로 의존성 주입이 가능하다 (생성자 주입)
     */

    private final EntityManager em;

    /* insert */
    public void save(Member member) {
        em.persist(member);
    }

    /* 단건조회 */
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    /* 다건조회, JPQL (From의 대상이 Entity) */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    /* 이름에 의해서 조회, Parameter Binding */
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
