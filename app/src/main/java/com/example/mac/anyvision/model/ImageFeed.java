package com.example.mac.anyvision.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "enclosure", strict = false)
public class ImageFeed {

    @Element(name = "_url")
    private String image;

    public String getImage() {
        return image;
    }
}
