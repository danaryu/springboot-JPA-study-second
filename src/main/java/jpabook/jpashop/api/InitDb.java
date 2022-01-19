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
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("danadot", "서울", "황학동", "210523");
            em.persist(member);

            Book bookA = createBook("heepong book", 20000, 200);
            em.persist(bookA);

            Book bookB = createBook("meepong book", 30000, 300);
            em.persist(bookB);

            OrderItem orderItem1 = OrderItem.createOrderItem(bookA, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(bookB, 20000, 2);

            Delivery delivery = createDelivery(member);

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Member member = createMember("heejae", "포항", "연일읍", "194755");
            em.persist(member);

            Book bookA = createBook("heepong", 20000, 100);
            em.persist(bookA);

            Book bookB = createBook("meepong", 30000, 100);
            em.persist(bookB);

            OrderItem orderItem1 = OrderItem.createOrderItem(bookA, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(bookB, 30000, 4);

            Delivery delivery = createDelivery(member);

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
    }

    private static Delivery createDelivery(Member member) {
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        return delivery;
    }

    private static Book createBook(String heepong_book, int price, int stockQuantity) {
        Book bookA = new Book();
        bookA.setName(heepong_book);
        bookA.setPrice(price);
        bookA.setStockQuantity(stockQuantity);
        return bookA;
    }

    private static Member createMember(String name, String city, String street, String zipcode) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address(city, street, zipcode));
        return member;
    }

}
