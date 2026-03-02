package core.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:src/test/resources/features/config.properties"
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

    @Key("username")
    String username();

    @Key("password")
    String password();
}
