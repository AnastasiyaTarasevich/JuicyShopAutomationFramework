package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class.getName());
    private static Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
                log.info("load properties success");
            } else {
                log.warn("Config file not found, will use environment variables");
            }
        } catch (IOException e) {
            log.error("load properties failed, will use environment variables", e);
        }
    }

    public static String getBaseUrl() {
        return System.getenv().getOrDefault("BASE_URL", properties.getProperty("base.url"));
    }

    public static String getUserLogin() {
        return System.getenv().getOrDefault("LOGIN_USER", properties.getProperty("login.user"));
    }

    public static String getUserPassword() {
        return System.getenv().getOrDefault("LOGIN_PASSWORD", properties.getProperty("login.password"));
    }

}
