package com.example.mac.anyvision.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

//@NamespaceList({
//        @Namespace(reference = "http://www.w3.org/2005/Atom", prefix = "atom")
//})
@Root(strict = false)
public class Channel {

    @ElementList(name = "item", required = true, inline = true)
    public List<FeedItem> itemList;
}
