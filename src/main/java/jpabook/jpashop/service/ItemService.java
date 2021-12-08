package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int quantity) {

        Item findItem = itemRepository.findOne(itemId); // 영속상태 Entity

        // Parameter로 들어온 Paramter를 item에 Mapping한다. (권장하지 않는 방법)
        /*
            findItem.setPrice(form.getPrice());
            findItem.setName(form.getName());
            findItem.setStockQuantity(form.getQuantity());
         */

        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(quantity);
        // -> @Transactional Annotation에 의해 Commit이 이루어짐 -> flush();
        // -> JPA에서 변경이 감지되어 DB Update

    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
