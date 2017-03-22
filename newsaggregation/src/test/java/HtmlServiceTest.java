import newsaggregator.entity.Item;
import newsaggregator.entity.ParseRule;
import newsaggregator.service.HtmlService;
import newsaggregator.service.ParseRuleService;
import newsaggregator.service.RssService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotEquals;


public class HtmlServiceTest {
    private HtmlService htmlService;
    private ParseRuleService parseRuleService;

    @Before
    public void setUp() throws Exception {
        htmlService = new HtmlService();
        parseRuleService = new ParseRuleService();
    }

    @Test
    public void testGetItemsYandexNews() {
        String userInput = "type=user\n" +
                "content-type=html\n" +
                "feed-tag=div\n" +
                "feed-class=rubric\n" +
                "channel-tag=a\n" +
                "channel-className=title\n" +
                "item-class=story__content\n" +
                "item-tag=div\n" +
                "title-class=story__title\n" +
                "title-tag=a\n" +
                "description-class=story__text\n" +
                "description-tag=div";
        ParseRule rule = parseRuleService.parseStringRule(userInput);
        List<Item> items = htmlService.getItems("https://news.yandex.ru/", rule);
        for (Item item : items) {
            assertNotEquals("", item.getTitle());
            assertNotEquals("", item.getDescription());
            Logger.getLogger(RssServiceTest.class.getName()).log(Level.SEVERE, "firstItem={\n" + item.getTitle() + "\n" + item.getDescription() + "\n}");
            System.out.println();
        }
    }

    @Test
    public void testGetItemsMailRuPolitics() {
        String userInput = "type=user\n" +
                "content-type=html\n" +
                "feed-tag=div\n" +
                "feed-class=cols\n" +
                "channel-tag=a\n" +
                "channel-className=title\n" +
                "item-class=newsitem\n" +
                "item-tag=div\n" +
                "title-class=newsitem__title\n" +
                "title-tag=a\n" +
                "description-class=newsitem__text\n" +
                "description-tag=div";
        ParseRule rule = parseRuleService.parseStringRule(userInput);
        List<Item> items = htmlService.getItems("https://news.mail.ru/politics/", rule);
        for (Item item : items) {
            assertNotEquals("", item.getTitle());
            assertNotEquals("", item.getDescription());
            Logger.getLogger(RssServiceTest.class.getName()).log(Level.SEVERE, "firstItem={\n" + item.getTitle() + "\n" + item.getDescription() + "\n}");
            System.out.println();
        }
    }
}
