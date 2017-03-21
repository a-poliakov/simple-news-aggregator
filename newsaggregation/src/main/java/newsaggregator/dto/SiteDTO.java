package newsaggregator.dto;


import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.Size;

public class SiteDTO {
    @Size(min = 1, message = "Invalid URL!")
    @URL(message = "Invalid URL!")
    private String url;

    @Size(min = 1, message = "Name must be at least 1 character!")
    private String name;

    private String parseRule;

    public SiteDTO() {
    }

    public SiteDTO(String url, String name, String parseRule) {
        this.url = url;
        this.name = name;
        this.parseRule = parseRule;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParseRule() {
        return parseRule;
    }

    public void setParseRule(String parseRule) {
        this.parseRule = parseRule;
    }
}
