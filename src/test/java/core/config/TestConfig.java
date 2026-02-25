package core.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config.properties"
})
public interface TestConfig extends Config {
    @Key("base.url")
    String baseUrl();

    @Key("api.url")
    String apiUrl();

    @Key("browser")
    String browser();

    @Key("timeout")
    int timeout();
}
