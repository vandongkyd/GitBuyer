package com.fernandocejas.android10.restrofit.repository.buyer;

import com.fernandocejas.android10.order.domain.User;
import com.fernandocejas.android10.order.domain.buyer.User_Buyer;
import com.fernandocejas.android10.order.domain.repository.UserRepository;
import com.fernandocejas.android10.order.domain.repository.buyer.UserRepository_Buyer;
import com.fernandocejas.android10.restrofit.enity.mapper.UserEntityDataMapper;
import com.fernandocejas.android10.restrofit.enity.mapper.buyer.UserEntityDataMapper_Buyer;
import com.fernandocejas.android10.restrofit.net.RetrofitHelper;
import com.fernandocejas.android10.restrofit.net.buyer.RetrofitHelper_Buyer;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by vandongluong on 3/12/18.
 */
@Singleton
public class UserDataRepository_Buyer implements UserRepository_Buyer {

    private final RetrofitHelper_Buyer retrofitHelper;

    private final UserEntityDataMapper_Buyer userEntityDataMapper;

    /**
     * Constructs a {@link UserRepository}.
     *
     * @param retrofitHelper
     * @param userEntityDataMapper
     */
    @Inject
    UserDataRepository_Buyer(
            RetrofitHelper_Buyer retrofitHelper,
            UserEntityDataMapper_Buyer userEntityDataMapper) {
        this.retrofitHelper = retrofitHelper;
        this.userEntityDataMapper = userEntityDataMapper;
    }


    @Override
    public Observable<User_Buyer> auto_signIn_buyer(String token) {
        return retrofitHelper
                .getRestApiService()
                .autoSignIn_buyer("Bearer " + token)
                .map(this.userEntityDataMapper::transform);
    }

    @Override
    public Observable<User_Buyer> signUp_buyer(String name,
                                   String email,
                                   String password,
                                   String phone,
                                   String type,
                                   String phone_code) {
        return retrofitHelper
                .getRestApiService()
                .signUp_buyer(name,
                        email,
                        password,
                        phone,
                        type,
                        phone_code)
                .map(this.userEntityDataMapper::transform);
    }

    @Override
    public Observable<User_Buyer> signIn_buyer(String email, String password) {
        return retrofitHelper
                .getRestApiService()
                .signIn_buyer(email, password)
                .map(this.userEntityDataMapper::transform);
    }

    @Override
    public Observable<User_Buyer> active_buyer(String token, String active_code) {
        return retrofitHelper
                .getRestApiService()
                .activation_buyer("Bearer " + token, active_code)
                .map(this.userEntityDataMapper::transform);
    }

    @Override
    public Observable<User_Buyer> resendActiveCode_buyer(String token) {
        return retrofitHelper
                .getRestApiService()
                .resendActivationCode_buyer("Bearer " + token)
                .map(this.userEntityDataMapper::transform);
    }

    @Override
    public Observable<User_Buyer> retrieveUserInfo_buyer(String token) {
        return retrofitHelper
                .getRestApiService()
                .user_info_buyer("Bearer " + token)
                .map(this.userEntityDataMapper::transform);
    }

    @Override
    public Observable<User_Buyer> changePassword_buyer(String token, String old_password, String password) {
        return retrofitHelper
                .getRestApiService()
                .change_password_buyer("Bearer " + token, old_password, password)
                .map(this.userEntityDataMapper::transform);
    }

    @Override
    public Observable<User_Buyer> updateUserInfo_buyer(String token, String name, String phone, String avatar) {
        return retrofitHelper
                .getRestApiService()
                .update_profile_buyer("Bearer " + token, name, phone, avatar)
                .map(this.userEntityDataMapper::transform);
    }
}
