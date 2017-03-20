package newsaggregator.repository;

import newsaggregator.entity.Item;
import newsaggregator.entity.Site;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findBySite(Site site, Pageable pageable);

    Item findBySiteAndLink(Site site, String link);

    List<Item> findByDescriptionContainsOrTitleContainsAllIgnoreCase(String descriptionPart,
                                                                     String titlePart, Pageable pageable);

    int countByDescriptionContainsOrTitleContainsAllIgnoreCase(String descriptionPart,
                                                                String titlePart);
}
