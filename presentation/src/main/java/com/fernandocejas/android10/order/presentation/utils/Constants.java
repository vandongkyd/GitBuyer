package com.fernandocejas.android10.order.presentation.utils;

import com.fernandocejas.android10.order.domain.Country;
import com.fernandocejas.android10.order.domain.Product;
import com.fernandocejas.android10.order.domain.buyer.User_Buyer;
import com.fernandocejas.android10.order.presentation.model.UserModel;
import com.fernandocejas.android10.order.presentation.model.buyer.UserModel_Buyer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 *
 */

public class Constants {

    public static final String REGISTER_TYPE = "1";

    public static final String USER_NOT_ACTIVE = "0";
    public static final String USER_ACTIVE = "1";

    public static HashMap<String, Country> COUNTRIES;

    public static HashMap<String, Product> PRODUCTS;
    public static Country COUNTRY = null;
    public static String CURRENCY_SHIP = null;

    public static User_Buyer USER;

}
