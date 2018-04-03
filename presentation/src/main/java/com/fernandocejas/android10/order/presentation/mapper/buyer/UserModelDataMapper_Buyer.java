/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.order.presentation.mapper.buyer;

import com.fernandocejas.android10.order.domain.buyer.User_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.UserModel_Buyer;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

@PerActivity
public class UserModelDataMapper_Buyer {

    @Inject
    public UserModelDataMapper_Buyer() {
    }

    public UserModel_Buyer transform(User_Buyer user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final UserModel_Buyer userModel = new UserModel_Buyer();
        userModel.setId(user.getId());
        userModel.setName(user.getName());
        userModel.setEmail(user.getEmail());
        userModel.setPhone(user.getPhone());
        userModel.setPhonecode(user.getPhonecode());
        userModel.setAvatar(user.getAvatar());
        userModel.setStatus(user.getStatus());
        userModel.setType(user.getType());
        userModel.setDocument(user.getDocument());
        userModel.setUpdated_at(user.getUpdated_at());
        userModel.setCreated_at(user.getCreated_at());
        userModel.setToken(user.getToken());
        return userModel;
    }

    public Collection<UserModel_Buyer> transform(Collection<User_Buyer> userCollection) {
        Collection<UserModel_Buyer> userModelCollection;

        if (userCollection != null && !userCollection.isEmpty()) {
            userModelCollection = new ArrayList<>();
            for (User_Buyer user : userCollection) {
                userModelCollection.add(transform(user));
            }
        } else {
            userModelCollection = Collections.emptyList();
        }

        return userModelCollection;
    }

    public User_Buyer transform(UserModel_Buyer user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final User_Buyer userBuyer = new User_Buyer();
        userBuyer.setId(user.getId());
        userBuyer.setName(user.getName());
        userBuyer.setEmail(user.getEmail());
        userBuyer.setPhone(user.getPhone());
        userBuyer.setPhonecode(user.getPhonecode());
        userBuyer.setAvatar(user.getAvatar());
        userBuyer.setStatus(user.getStatus());
        userBuyer.setType(user.getType());
        userBuyer.setDocument(user.getDocument());
        userBuyer.setUpdated_at(user.getUpdated_at());
        userBuyer.setCreated_at(user.getCreated_at());
        userBuyer.setToken(user.getToken());
        return userBuyer;
    }
}

