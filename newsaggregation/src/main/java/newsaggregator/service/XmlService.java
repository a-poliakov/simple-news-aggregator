package newsaggregator.service;

import newsaggregator.entity.Item;
import newsaggregator.entity.ParseRule;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
public class XmlService {
    public List<Item> getItems(String url, ParseRule rule){
        throw new NotImplementedException();
    }
}
