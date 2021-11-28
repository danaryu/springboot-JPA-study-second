package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    // Spring Data JPA가 Autowired를 통해 injection 주입
    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { // JPA에 저장하기 전까지 id값이 없음. 없는 경우 "완전히 새로운 객체"
            em.persist(item);
        } else {
            em.merge(item); // update와 비슷한 기능 (강제)
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
