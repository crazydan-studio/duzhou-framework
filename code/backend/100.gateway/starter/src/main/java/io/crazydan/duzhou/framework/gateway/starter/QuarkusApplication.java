package io.crazydan.duzhou.framework.gateway.starter;

import io.nop.boot.NopApplication;
import io.nop.core.initialize.CoreInitialization;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.event.Observes;

@QuarkusMain
public class QuarkusApplication {
    private static String[] globalArgs;

    public static void main(String... args) {
        globalArgs = args;

        Quarkus.run(args);
    }

    public void start(@Observes StartupEvent event) {
        QuarkusIntegration.start();

        NopApplication app = new NopApplication();
        app.run(globalArgs);
    }

    public void stop(@Observes ShutdownEvent event) {
        CoreInitialization.destroy();
    }
}
