package core.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigManager {

    private static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class, System.getProperties());

    public static TestConfig getConfig() {
        return CONFIG;
    }
}
