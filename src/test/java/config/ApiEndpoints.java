package config;

public class ApiEndpoints {
    public static final String LOGIN = "/rest/user/login";
    public static final String REGISTER = "/api/Users/";

    public static final String GET_CURRENT_USER = "/rest/user/whoami";

    public static final String ALL_PRODUCTS = "/rest/products/search";
    public static final String GET_PRODUCT_QUANTITIES = "/api/Quantitys/";

    public static final String ADD_ITEM_TO_BASKET = "/api/BasketItems/";
    public static final String DELETE_BASKET_ITEM = "/api/BasketItems/{basketItemId}";
    public static final String GET_BASKET = "/rest/basket/{id}";

}
