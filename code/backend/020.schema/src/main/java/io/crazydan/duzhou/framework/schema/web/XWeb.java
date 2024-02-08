package io.crazydan.duzhou.framework.schema.web;

import io.crazydan.duzhou.framework.schema.web._gen._XWeb;
import io.nop.xlang.xdsl.DslModelParser;

public class XWeb extends _XWeb {

    public XWeb() {

    }

    public static XWeb parseFromVirtualPath(String path) {
        // Note：在 DSL 中已经指定 x:schema，无需在 DslModelParser 构造参数中再指定
        return (XWeb) new DslModelParser().parseFromVirtualPath(path);
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
