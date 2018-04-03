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
package com.fernandocejas.android10.order.domain.interactor.buyer;

import com.fernandocejas.android10.order.domain.buyer.User_Buyer;
import com.fernandocejas.android10.order.domain.repository.buyer.UserRepository_Buyer;
import com.fernandocejas.android10.sample.domain.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.domain.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.domain.interactor.UseCase;
import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * active user.
 */
public class UpdateUserInfo_Buyer extends UseCase<User_Buyer, UpdateUserInfo_Buyer.Params> {

    private final UserRepository_Buyer userRepository;

    @Inject
    UpdateUserInfo_Buyer(UserRepository_Buyer userRepository, ThreadExecutor threadExecutor,
                         PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<User_Buyer> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return this.userRepository.updateUserInfo_buyer(params.token,
                params.name,
                params.phone,
                params.avatar);
    }

    public static final class Params {

        final String token;
        final String name;
        final String phone;
        final String avatar;

        private Params(String token,
                       String name,
                       String phone,
                       String avatar) {
            this.token = token;
            this.name = name;
            this.phone = phone;
            this.avatar = avatar;
        }

        public static UpdateUserInfo_Buyer.Params forUpdateUserInfo(String token,
                                                                    String name,
                                                                    String phone,
                                                                    String avatar) {
            return new UpdateUserInfo_Buyer.Params(token, name, phone, avatar);
        }
    }
}
