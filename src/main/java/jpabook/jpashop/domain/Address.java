package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

// 내장 타입
@Embeddable
@Getter // Getter만 기본으로 사용
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // 직접 호출해서 사용하지 못하도록! JPA 스펙상 사용할 수 있도록 (기본생성자 반드시 필요)
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
