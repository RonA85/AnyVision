package com.example.mac.anyvision.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "enclosure", strict = false)
public class ImageFeed {
    @Attribute(name = "url")
    private String url;

    public String getImage() {
        return url;
    }
}
