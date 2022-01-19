package jpabook.jpashop.api;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = new Member();
            member.setName("danadot");
            member.setAddress(new Address("서울", "황학동", "210523"));
            em.persist(member);

            Book bookA = new Book();
            bookA.setName("heepong");
            bookA.setPrice(10000);
            bookA.setStockQuantity(100);
            em.persist(bookA);

            Book bookB = new Book();
            bookB.setName("meepong");
            bookB.setPrice(10000);
            bookB.setStockQuantity(100);
            em.persist(bookB);

            OrderItem orderItem1 = OrderItem.createOrderItem(bookA, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(bookB, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }
    }

}
