package newsaggregator.service;

import newsaggregator.entity.Item;
import newsaggregator.entity.Site;
import newsaggregator.exception.RssException;
import newsaggregator.repository.ItemRepository;
import newsaggregator.repository.SiteRepository;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {
    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private RssService rssService;

    @Autowired
    private ItemRepository itemRepository;

    public void saveItems(Site site){
        try{
            List<Item> items = rssService.getItems(site.getUrl());
            for (Item item : items) {
                Item savedItem = itemRepository.findBySiteAndLink(site, item.getLink());
                if(savedItem == null) {
                    item.setSite(site);
                    itemRepository.save(item);
                }
            }
        } catch (RssException e) {
            e.printStackTrace();
        }
    }

    // 1 hour = 60 seconds * 60 minutes * 1000 = 3600000
    // 1 minute = 60 seconds * 1000 = 60000
    @Scheduled(fixedDelay=60000)
    public void reloadSites(){
        List<Site> sites = siteRepository.findAll();
        for (Site site : sites) {
            saveItems(site);
        }
    }

    public void save(Site site){
        siteRepository.save(site);
        saveItems(site);
    }

    public void delete(Site site) {
        siteRepository.delete(site);
    }

    public Site findOne(int id) {
        return siteRepository.findOne(id);
    }

    public List<Site>  findAll() {
        return siteRepository.findAll(new PageRequest(0, 20, Sort.Direction.ASC, "name")).getContent();
    }
}
