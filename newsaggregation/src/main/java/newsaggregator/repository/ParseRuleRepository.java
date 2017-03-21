package newsaggregator.repository;

import newsaggregator.entity.ParseRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParseRuleRepository extends JpaRepository<ParseRule, Integer> {
}
