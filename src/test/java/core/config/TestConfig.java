package core.config;

import core.utils.OwnerDecryptor;
import org.aeonbits.owner.Config;

@Config.Sources({
        "file:src/test/resources/config.properties"
})
@Config.DecryptorClass(OwnerDecryptor.class)
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
    @EncryptedValue
    String password();

    @Key("db.url")
    String dbUrl();

    @Key("db.user")
    String dbUser();

    @Key("db.password")
    String dbPassword();
}
