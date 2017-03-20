import newsaggregator.entity.Item;
import newsaggregator.exception.RssException;
import newsaggregator.service.RssService;
import org.junit.Before;
import org.junit.Test;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RssServiceTest {
    private RssService rssService;

    @Before
    public void setUp() throws Exception {
        rssService = new RssService();
    }

    @Test
    public void testGetItemsFile() throws RssException {
        List<Item> items = rssService.getItems("http://www.tomcatexpert.com/blog/feed");
        assertEquals(10, items.size());
        Item firstItem = items.get(0);
        assertEquals("How to generate web.xml in Eclipse", firstItem.getTitle());
        assertEquals("23 03 2014 09:01:34", new SimpleDateFormat("dd MM yyyy HH:mm:ss").format(firstItem.getPublishedDate()));
    }
}
