package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item", //  다대다 관계에서는 중간 테이블이 있어야 관계형 DB로 표현이 가능하다. 테이블 명
            joinColumns = @JoinColumn(name = "category_id"), // 중간 테이블에 Mapping 할 category id
            inverseJoinColumns = @JoinColumn(name = "item_id")) // FK
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

}
