package newsaggregator.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ParseRule {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 1000)
    private String type = "";
    @Column(length = 1000, name = "content_type")
    private String contentType = "";
    @Column(length = 1000, name = "feed_tag")
    private String feedTag = "";
    @Column(length = 1000)
    private String feedClass = "";
    @Column(length = 1000)
    private String channelTag = "";
    @Column(length = 1000)
    private String channelClassName = "";
    @Column(length = 1000)
    private String itemClass = "";
    @Column(length = 1000)
    private String itemTag = "";
    @Column(length = 1000)
    private String titleClass = "";
    @Column(length = 1000)
    private String titleTag = "";
    @Column(length = 1000)
    private String descriptionClass = "";
    @Column(length = 1000)
    private String descriptionTag = "";
    @Column(length = 1000)
    private String publishedDateClass = "";
    @Column(length = 1000)
    private String publishedDateTag = "";
    @Column(length = 1000)
    private String linkClass = "";
    @Column(length = 1000)
    private String linkTag = "";

    @OneToOne(mappedBy = "parseRule", cascade = CascadeType.ALL)
    private Site site;

    public ParseRule() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFeedTag() {
        return feedTag;
    }

    public void setFeedTag(String feedTag) {
        this.feedTag = feedTag;
    }

    public String getFeedClass() {
        return feedClass;
    }

    public void setFeedClass(String feedClass) {
        this.feedClass = feedClass;
    }

    public String getChannelTag() {
        return channelTag;
    }

    public void setChannelTag(String channelTag) {
        this.channelTag = channelTag;
    }

    public String getChannelClassName() {
        return channelClassName;
    }

    public void setChannelClassName(String channelClassName) {
        this.channelClassName = channelClassName;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getItemTag() {
        return itemTag;
    }

    public void setItemTag(String itemTag) {
        this.itemTag = itemTag;
    }

    public String getTitleClass() {
        return titleClass;
    }

    public void setTitleClass(String titleClass) {
        this.titleClass = titleClass;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public void setTitleTag(String titleTag) {
        this.titleTag = titleTag;
    }

    public String getDescriptionClass() {
        return descriptionClass;
    }

    public void setDescriptionClass(String descriptionClass) {
        this.descriptionClass = descriptionClass;
    }

    public String getDescriptionTag() {
        return descriptionTag;
    }

    public void setDescriptionTag(String descriptionTag) {
        this.descriptionTag = descriptionTag;
    }

    public String getPublishedDateClass() {
        return publishedDateClass;
    }

    public void setPublishedDateClass(String publishedDateClass) {
        this.publishedDateClass = publishedDateClass;
    }

    public String getPublishedDateTag() {
        return publishedDateTag;
    }

    public void setPublishedDateTag(String publishedDateTag) {
        this.publishedDateTag = publishedDateTag;
    }

    public String getLinkClass() {
        return linkClass;
    }

    public void setLinkClass(String linkClass) {
        this.linkClass = linkClass;
    }

    public String getLinkTag() {
        return linkTag;
    }

    public void setLinkTag(String linkTag) {
        this.linkTag = linkTag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
