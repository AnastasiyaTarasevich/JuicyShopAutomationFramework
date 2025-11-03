package pages;

public enum LoginPage implements PageElement {

    EMAIL("email", true),
    PASSWORD("password", true),
    LOGIN_BUTTON("loginButton", false);

    private final String id;
    private final boolean required;
    public final String pageName = "LOGIN PAGE";

    LoginPage(String id, boolean required) {
        this.id = id;
        this.required = required;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public String getPageName() {
        return pageName;
    }

    public static PageElement[] getRequiredElements() {
        return values();
    }
}
