package newsaggregator.service;

import newsaggregator.entity.Item;
import newsaggregator.entity.Site;
import newsaggregator.repository.ItemRepository;
import newsaggregator.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;

@Transactional
@Service
public class InitDbService {
    @Autowired
    private SiteRepository blogRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostConstruct
    public void init() {
//        Site siteJavavids = new Site();
//        siteJavavids.setName("JavaVids");
//        siteJavavids
//                .setUrl("http://feeds.feedburner.com/javavids?format=xml");
//        blogRepository.save(siteJavavids);
    }
}
