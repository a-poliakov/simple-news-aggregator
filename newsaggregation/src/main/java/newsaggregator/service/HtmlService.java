package newsaggregator.service;

import newsaggregator.entity.Item;
import newsaggregator.entity.ParseRule;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class HtmlService {

    public List<Item> getItems(String url, ParseRule rule)  {
        final ArrayList<Item> list = new ArrayList<Item>();
        try {
            Connection.Response connection = null;
            connection = Jsoup.connect(url).execute();
            Document document = connection.parse();
            if (!rule.getFeedClass().equals("")) {
                document.body().getElementsByClass(rule.getFeedClass()).forEach(getFeedConsumer(rule, list));
            } else if(!rule.getFeedTag().equals("")){
                document.body().getElementsByTag(rule.getFeedTag()).forEach(getFeedConsumer(rule, list));
            } else{
                if(!rule.getItemClass().equals("")) {
                    document.body().getElementsByTag(rule.getItemClass()).forEach(getElementConsumer(rule, list));
                } else if(!rule.getItemTag().equals("")){
                    document.body().getElementsByTag(rule.getItemTag()).forEach(getElementConsumer(rule, list));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(HtmlService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private Consumer<Element> getFeedConsumer(ParseRule rule, ArrayList<Item> list) {
        return value -> {
            try {
                if (!rule.getItemClass().equals("")) {
                    value.getElementsByClass(rule.getItemClass()).forEach(getElementConsumer(rule, list));
                } else if (!rule.getItemTag().equals("")) {
                    value.getElementsByTag(rule.getItemTag()).forEach(getElementConsumer(rule, list));
                }
            } catch (Exception ex) {
                Logger.getLogger(HtmlService.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
    }

    private Consumer<Element> getElementConsumer(ParseRule rule, ArrayList<Item> list) {
        return e -> {
            try {
                String title = e.getElementsByClass(rule.getTitleClass()).tagName(rule.getTitleTag()).text();
                String description = e.getElementsByClass(rule.getDescriptionClass()).text();
                if (title.equals("") || description.equals(""))
                    return;
                Item item = new Item();
                item.setTitle(title);
                item.setDescription(description);
                if (!rule.getLinkClass().equals("")) {
                    item.setLink(e.getElementsByClass(rule.getLinkClass()).tagName(rule.getLinkTag()).text());
                }
                if (!rule.getPublishedDateClass().equals("")) {
                    try {
                        Date pubDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
                                .parse(e.getElementsByClass(rule.getPublishedDateClass()).tagName(rule.getPublishedDateTag()).text());
                        item.setPublishedDate(pubDate);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
                list.add(item);
            } catch (Exception ex){
                Logger.getLogger(HtmlService.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
    }
}
