package newsaggregator.service;


import newsaggregator.entity.Item;
import newsaggregator.entity.Site;
import newsaggregator.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public List<Item> getItems(int page, Integer size) {
        return itemRepository.findAll(new PageRequest(page, size, Sort.Direction.DESC, "publishedDate")).getContent();
    }

    public List<Item> getItems(int page, Integer size, String search) {
        return itemRepository.findAll(new PageRequest(page, size, Sort.Direction.DESC, "publishedDate")).getContent();
    }

    public long countMembers() {
        return itemRepository.count();
    }

    public List<Item>  search(String searchText, Integer size, int page) {
        return itemRepository.findByDescriptionContainsOrTitleContainsAllIgnoreCase(searchText, searchText, new PageRequest(page, size, Sort.Direction.DESC, "publishedDate"));
    }

    public int countBySearchPattern(String searchText){
        return itemRepository.countByDescriptionContainsOrTitleContainsAllIgnoreCase(searchText, searchText);
    }
}
