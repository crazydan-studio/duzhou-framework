package io.crazydan.duzhou.framework.schema.web;

import io.crazydan.duzhou.framework.schema.web._gen._XWeb;

public class XWeb extends _XWeb {
    public XWeb() {

    }

    public XWebSite getSiteByUrl(String url) {
        for (XWebSite site : getSites()) {
            if (site.getUrl().equals(url)) {
                return site;
            }
        }
        return null;
    }
}
