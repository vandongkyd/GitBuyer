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
 * retrieve user information.
 */

public class RefreshAccessToken_Buyer extends UseCase<User_Buyer, RefreshAccessToken_Buyer.Params> {
    private final UserRepository_Buyer userRepository;

    @Inject
    RefreshAccessToken_Buyer(UserRepository_Buyer userRepository, ThreadExecutor threadExecutor,
                             PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<User_Buyer> buildUseCaseObservable(RefreshAccessToken_Buyer.Params params) {
        Preconditions.checkNotNull(params);
        return this.userRepository.auto_signIn_buyer(params.token);
    }

    public static final class Params {

        private final String token;

        private Params(String token) {
            this.token = token;
        }

        public static RefreshAccessToken_Buyer.Params forRetrieveUserInformation(String token) {
            return new RefreshAccessToken_Buyer.Params(token);
        }
    }
}
