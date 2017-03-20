package newsaggregator.repository;

import newsaggregator.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Integer> {
}
