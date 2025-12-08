package config;

public class ApiEndpoints {
    public static final String LOGIN = "/rest/user/login";
    public static final String REGISTER = "/api/Users/";

    public static final String GET_CURRENT_USER = "/rest/user/whoami";
    public static final String LOGOUT_USER = "/rest/user/logout";

    public static final String ALL_PRODUCTS = "/rest/products/search";

    public static final String ADD_ITEM_TO_BASKET = "/api/BasketItems/";
    public static final String GET_BASKET = "/rest/basket/{id}";

}
