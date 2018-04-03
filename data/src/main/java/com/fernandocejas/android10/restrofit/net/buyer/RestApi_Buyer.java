package com.fernandocejas.android10.restrofit.net.buyer;

import com.fernandocejas.android10.restrofit.enity.UserEntity;
import com.fernandocejas.android10.restrofit.enity.UserEntityResponse;
import com.fernandocejas.android10.restrofit.enity.buyer.AddressEntityResponse_Buyer;
import com.fernandocejas.android10.restrofit.enity.buyer.AddressListEntityResponse_Buyer;
import com.fernandocejas.android10.restrofit.enity.buyer.OfferEntityResponse_Buyer;
import com.fernandocejas.android10.restrofit.enity.buyer.OfferListEntityResponse_Buyer;
import com.fernandocejas.android10.restrofit.enity.buyer.OrderListEntityResponse_Buyer;
import com.fernandocejas.android10.restrofit.enity.buyer.UserEntityResponse_Buyer;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by vandongluong on 3/12/18.
 */

public interface RestApi_Buyer {

    /**
     * Retrieves an {@link Observable} which will emit a Object of {@link UserEntity}.
     */
    @POST("/apikoda/auto_signin")
    Observable<UserEntityResponse_Buyer> autoSignIn_buyer(
            @Header("Authorization") String token
    );

    /**
     * Retrieves an {@link Observable} which will emit a Object of {@link UserEntity}.
     */
    @POST("/apikoda/signin")
    @FormUrlEncoded
    Observable<UserEntityResponse_Buyer> signIn_buyer(
            @Field("email") String email,
            @Field("password") String password
    );

    /**
     * Retrieves an {@link Observable} which will emit a Object of {@link UserEntity}.
     */
    @POST("/apikoda/signup")
    @FormUrlEncoded
    Observable<UserEntityResponse_Buyer> signUp_buyer(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone,
            @Field("type") String type,
            @Field("phonecode") String phone_code
    );

    /**
     * Retrieves an {@link Observable} which will emit a Object of {@link UserEntity}.
     */
    @POST("/apikoda/activation")
    @FormUrlEncoded
    Observable<UserEntityResponse_Buyer> activation_buyer(
            @Header("Authorization") String token,
            @Field("code") String code
    );

    @POST("/apikoda/resend_activation_code")
    Observable<UserEntityResponse_Buyer> resendActivationCode_buyer(
            @Header("Authorization") String token
    );

    @GET("/apikoda/user_info")
    Observable<UserEntityResponse_Buyer> user_info_buyer(
            @Header("Authorization") String token
    );

    @POST("/apikoda/change_password")
    @FormUrlEncoded
    Observable<UserEntityResponse_Buyer> change_password_buyer(
            @Header("Authorization") String token,
            @Field("old_password") String old_password,
            @Field("new_password") String password
    );

    @POST("/apikoda/update_profile")
    @FormUrlEncoded
    Observable<UserEntityResponse_Buyer> update_profile_buyer(
            @Header("Authorization") String token,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("avatar") String avatar
    );

    @GET("/apikoda/addresses")
    Observable<AddressListEntityResponse_Buyer> addresses_buyer(
            @Header("Authorization") String token
    );

    @POST("/apikoda/new_address")
    @FormUrlEncoded
    Observable<AddressEntityResponse_Buyer> new_address_buyer(
            @Header("Authorization") String token,
            @Field("address") String address,
            @Field("city") String city,
            @Field("postcode") String postcode,
            @Field("country") String country,
            @Field("country code") String country_code
    );

    @GET("/apikoda/buyer/orders?offset=0")
    Observable<OrderListEntityResponse_Buyer> order_buyer(
            @Header("Authorization") String token
    );

    @GET("/apikoda/buyer/my_orders?offset=0")
    Observable<OrderListEntityResponse_Buyer> my_order_buyer(
            @Header("Authorization") String token
    );

    @GET("/apikoda/buyer/my_offers?offset=0")
    Observable<OrderListEntityResponse_Buyer> my_offers_offset(
            @Header("Authorization") String token
    );

    @POST("/apikoda/buyer/make_offer")
    @FormUrlEncoded
    Observable<OfferEntityResponse_Buyer> make_offer(
            @Header("Authorization") String token,
            @Field("orderid") String orderid,
            @Field("deviverdate") String deviverdate,
            @Field("providerfee") String providerfee,
            @Field("shipfee") String shipfee,
            @Field("surchargefee") String surchargefee,
            @Field("otherfee") String otherfee,
            @Field("description") String description
    );

}
