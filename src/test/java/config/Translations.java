package config;

import java.util.Locale;
import java.util.ResourceBundle;
import utils.Language;

public class Translations {

    public static ResourceBundle getBundle(Language language) {
        return ResourceBundle.getBundle("localization.translations", new Locale(language.code));
    }

    public static String getProductTitle(Language language) {
        return getBundle(language).getString("products.title");
    }

    public static String getAccountButtonTitle(Language language) {
        return getBundle(language).getString("account.button");
    }
}
