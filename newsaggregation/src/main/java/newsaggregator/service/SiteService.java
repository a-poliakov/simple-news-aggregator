package newsaggregator.service;

import newsaggregator.dto.SiteDTO;
import newsaggregator.entity.Item;
import newsaggregator.entity.ParseRule;
import newsaggregator.entity.Site;
import newsaggregator.exception.RssException;
import newsaggregator.repository.ItemRepository;
import newsaggregator.repository.ParseRuleRepository;
import newsaggregator.repository.SiteRepository;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SiteService {
    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private RssService rssService;

    @Autowired
    private HtmlService htmlService;

    @Autowired
    private XmlService xmlService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ParseRuleService parseRuleService;

    public void saveItems(Site site){
        try{
            List<Item> items = new ArrayList<>();
            if(site.getParseRule() == null || site.getParseRule().getType().equals("") || site.getParseRule().getType().equals("auto")) {
                items = rssService.getItems(site.getUrl());
            } else if(site.getParseRule().getType().equals("user") && site.getParseRule().getContentType().equals("html")){
                items = htmlService.getItems(site.getUrl(), site.getParseRule());
            } else if(site.getParseRule().getType().equals("user") && site.getParseRule().getContentType().equals("xml")){
                items = xmlService.getItems(site.getUrl(), site.getParseRule());
            }
            for (Item item : items) {
                Item savedItem = itemRepository.findBySiteAndTitleAndLink(site, item.getTitle(), item.getLink());
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

    public void save(SiteDTO siteDto){
        Site site = new Site();
        site.setName(siteDto.getName());
        site.setUrl(siteDto.getUrl());
        ParseRule rule = parseRuleService.parseStringRule(siteDto.getParseRule());
        rule = parseRuleService.save(rule);
        site.setParseRule(rule);
        site = siteRepository.save(site);
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
