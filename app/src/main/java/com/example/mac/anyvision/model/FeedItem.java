package com.example.mac.anyvision.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class FeedItem {

    @Element(name = "title")
    private String title;
    @Element(name = "link")
    private String link;
    @Element(name = "pubDate")
    private String date;
    @Element(name = "description")
    private String description;
    @Element(required = false)
    private ImageFeed enclosure;

    public FeedItem() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageFeed getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(ImageFeed enclosure) {
        this.enclosure = enclosure;
    }
}
