package newsaggregator.service;

import newsaggregator.entity.Item;
import newsaggregator.entity.ParseRule;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
public class XmlService {
    public List<Item> getItems(String url, ParseRule rule)  {
        final ArrayList<Item> list = new ArrayList<Item>();
        try {
            Connection.Response connection = null;
            connection = Jsoup.connect(url).execute();
            Document document = connection.parse();
            if (!rule.getFeedTag().equals("")) {
                document.body().getElementsByTag(rule.getFeedTag()).forEach(getFeedConsumer(rule, list));
            } else if(!rule.getChannelTag().equals("")){
                document.getElementsByTag(rule.getChannelTag()).forEach(getFeedConsumer(rule, list));
            }
        } catch (IOException ex) {
            Logger.getLogger(HtmlService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private Consumer<Element> getFeedConsumer(ParseRule rule, ArrayList<Item> list) {
        return value -> {
            try {
                if (!rule.getItemTag().equals("")) {
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
                String title = e.getElementsByTag(rule.getTitleTag()).tagName(rule.getTitleTag()).text();
                String description = e.getElementsByTag(rule.getDescriptionTag()).text();
                if (title.equals("") || description.equals(""))
                    return;
                Item item = new Item();
                item.setTitle(title);
                item.setDescription(description);
                if (!rule.getLinkTag().equals("")) {
                    item.setLink(e.getElementsByTag(rule.getLinkTag()).tagName(rule.getLinkTag()).text());
                }
                if (!rule.getPublishedDateTag().equals("")) {
                    try {
                        Date pubDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
                                .parse(e.getElementsByTag(rule.getPublishedDateTag()).tagName(rule.getPublishedDateTag()).text());
                        item.setPublishedDate(pubDate);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        Date pubDate = new Date();
                        item.setPublishedDate(pubDate);
                    }
                } else {
                    Date pubDate = new Date();
                    item.setPublishedDate(pubDate);
                }
                list.add(item);
            } catch (Exception ex){
                Logger.getLogger(HtmlService.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
    }
}
