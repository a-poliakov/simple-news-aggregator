package newsaggregator.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 1000)
    private String title;

    @Lob
    @Type(type = "org.hibernate.type.StringType")
    @Column(length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "published_date")
    private Date publishedDate;

    @Column(length = 1000)
    private String link;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
