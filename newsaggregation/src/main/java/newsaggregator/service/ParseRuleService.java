package newsaggregator.service;

import newsaggregator.entity.ParseRule;
import newsaggregator.repository.ParseRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ParseRuleService {
    @Autowired
    private ParseRuleRepository parseRuleRepository;

    public ParseRule parseStringRule(String rule) {
        ParseRule parseRule = new ParseRule();
        if (rule.equals("")) {
            parseRule.setType("auto");
            return parseRule;
        }
        Map<String, String> ruleProperties = new HashMap<>();
        try {
            for (String s : rule.split("\n")) {
                String[] entries = s.split("=");
                ruleProperties.put(entries[0], entries[1]);
            }
        } catch (Exception e) {
            parseRule.setType("auto");
            return parseRule;
        }
        return applyParseProperties(parseRule, ruleProperties);
    }

    private ParseRule applyParseProperties(ParseRule parseRule, Map<String, String> ruleProperties){
        for (Map.Entry<String, String> entry: ruleProperties.entrySet()) {
            switch (entry.getKey()){
                case "type":
                    parseRule.setType(entry.getValue());
                    break;
                case "content-type":
                    parseRule.setContentType(entry.getValue());
                    break;
                case "feed-tag":
                    parseRule.setFeedTag(entry.getValue());
                    break;
                case "feed-class":
                    parseRule.setFeedClass(entry.getValue());
                    break;
                case "channel-tag":
                    parseRule.setChannelTag(entry.getValue());
                    break;
                case "channel-className":
                    parseRule.setChannelClassName(entry.getValue());
                    break;
                case "item-class":
                    parseRule.setItemClass(entry.getValue());
                    break;
                case "item-tag":
                    parseRule.setItemTag(entry.getValue());
                    break;
                case "title-tag":
                    parseRule.setTitleTag(entry.getValue());
                    break;
                case "title-class":
                    parseRule.setTitleClass(entry.getValue());
                    break;
                case "description-class":
                    parseRule.setDescriptionClass(entry.getValue());
                    break;
                case "description-tag":
                    parseRule.setDescriptionTag(entry.getValue());
                    break;
                case "publishedDate-class":
                    parseRule.setPublishedDateClass(entry.getValue());
                    break;
                case "publishedDate-tag":
                    parseRule.setDescriptionTag(entry.getValue());
                    break;
                case "link-class":
                    parseRule.setLinkClass(entry.getValue());
                    break;
                case "link-tag":
                    parseRule.setLinkTag(entry.getValue());
                    break;
            }
        }
        return parseRule;
    }

    public void save(ParseRule parseRule){
        parseRuleRepository.save(parseRule);
    }
}
