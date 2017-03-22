import newsaggregator.entity.Item;
import newsaggregator.entity.ParseRule;
import newsaggregator.service.HtmlService;
import newsaggregator.service.ParseRuleService;
import newsaggregator.service.XmlService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotEquals;

public class XmlServiceTest {
    private XmlService xmlService;
    private ParseRuleService parseRuleService;

    @Before
    public void setUp() throws Exception {
        xmlService = new XmlService();
        parseRuleService = new ParseRuleService();
    }

    @Test
    public void testGetItemsLentaRu() {
        String userInput = "type=user\n" +
                "content-type=xml\n" +
                "channel-tag=channel\n" +
                "item-tag=item\n" +
                "title-tag=title\n" +
                "description-tag=description\n" +
                "publishedDate-tag=pubDate\n" +
                "link-tag=link";
        ParseRule rule = parseRuleService.parseStringRule(userInput);
        List<Item> items = xmlService.getItems("https://lenta.ru/rss/", rule);
        for (Item item : items) {
            assertNotEquals("", item.getTitle());
            assertNotEquals("", item.getDescription());
            Logger.getLogger(RssServiceTest.class.getName()).log(Level.SEVERE, "firstItem={\n" + item.getTitle() + "\n" + item.getDescription() + "\n}");
            System.out.println();
        }
    }
}
