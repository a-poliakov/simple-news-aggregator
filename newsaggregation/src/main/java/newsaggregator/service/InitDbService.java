package newsaggregator.service;

import newsaggregator.entity.Item;
import newsaggregator.entity.ParseRule;
import newsaggregator.entity.Site;
import newsaggregator.repository.ItemRepository;
import newsaggregator.repository.ParseRuleRepository;
import newsaggregator.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
@Service
public class InitDbService {
    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ParseRuleRepository ruleRepository;

    @PostConstruct
    public void init() {
        if (siteRepository.count() < 1) {
            ParseRule rule = new ParseRule();
            rule.setType("auto");
            rule = ruleRepository.save(rule);
            Site siteJavavids = new Site();
            siteJavavids.setName("JavaVids");
            siteJavavids.setUrl("http://feeds.feedburner.com/javavids?format=xml");
            siteJavavids.setParseRule(rule);
            siteRepository.save(siteJavavids);
        }
    }
}
