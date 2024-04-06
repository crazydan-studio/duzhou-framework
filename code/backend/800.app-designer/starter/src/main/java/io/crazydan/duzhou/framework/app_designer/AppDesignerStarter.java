
package io.crazydan.duzhou.framework.app_designer;

import io.crazydan.duzhou.framework.starter.QuarkusStarter;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * Quarkus 应用启动器
 * <p/>
 * 该启动器只能在应用服务中实现，否则，将不会扫描到应用服务的
 * classpath 中的资源。
 */
@QuarkusMain
public class AppDesignerStarter implements QuarkusApplication {

    public static void main(String... args) {
        // https://quarkus.io/guides/lifecycle#the-main-method
        Quarkus.run(AppDesignerStarter.class, QuarkusStarter::stop, args);
    }

    @Override
    public int run(String... args) throws Exception {
        return QuarkusStarter.start(args);
    }
}
