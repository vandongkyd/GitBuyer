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
 * change password.
 */

public class ChangePassword_Buyer extends UseCase<User_Buyer, ChangePassword_Buyer.Params> {
    private final UserRepository_Buyer userRepository;

    @Inject
    ChangePassword_Buyer(UserRepository_Buyer userRepository, ThreadExecutor threadExecutor,
                         PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<User_Buyer> buildUseCaseObservable(ChangePassword_Buyer.Params params) {
        Preconditions.checkNotNull(params);
        return this.userRepository.changePassword_buyer(params.token, params.old_password, params.password);
    }

    public static final class Params {

        private final String token;
        private final String old_password;
        private final String password;


        private Params(String token, String old_password, String password) {
            this.token = token;
            this.old_password = old_password;
            this.password = password;
        }

        public static ChangePassword_Buyer.Params forChangePassword(String token, String old_password, String password) {
            return new ChangePassword_Buyer.Params(token, old_password, password);
        }
    }
}
