import newsaggregator.entity.Item;
import newsaggregator.exception.RssException;
import newsaggregator.service.HtmlService;
import newsaggregator.service.RssService;
import org.junit.Before;
import org.junit.Test;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RssServiceTest {
    private RssService rssService;

    @Before
    public void setUp() throws Exception {
        rssService = new RssService();
    }

    @Test
    public void testGetItemsRss() throws RssException {
        List<Item> items = rssService.getItems("http://www.tomcatexpert.com/blog/feed");
        assertEquals(10, items.size());
        Item firstItem = items.get(0);
        assertNotEquals("", firstItem.getTitle());
        assertNotEquals("", firstItem.getDescription());
        Logger.getLogger(RssServiceTest.class.getName()).log(Level.SEVERE, "firstItem={\n" + firstItem.getTitle() + "\n" +firstItem.getDescription() + "\n}");
        //assertEquals("23 03 2014 09:01:34", new SimpleDateFormat("dd MM yyyy HH:mm:ss").format(firstItem.getPublishedDate()));
    }
}
