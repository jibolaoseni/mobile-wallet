package org.mifos.mobilewallet.user.presenter;

import org.mifos.mobilewallet.core.BaseView;
import org.mifos.mobilewallet.core.UseCase;
import org.mifos.mobilewallet.core.UseCaseHandler;
import org.mifos.mobilewallet.user.UserContract;
import org.mifos.mobilewallet.user.domain.usecase.FetchUserDetails;
import org.mifos.mobilewallet.user.domain.usecase.VerifyPanDetails;

import javax.inject.Inject;

/**
 * Created by naman on 22/6/17.
 */

public class UserDetailsPresenter implements UserContract.UserDetailsPresenter {

    private UserContract.UserDetailsView mUserDetailsView;
    private final UseCaseHandler mUsecaseHandler;

    @Inject
    FetchUserDetails fetchUserData;

    @Inject
    VerifyPanDetails verifyPanDetails;

    @Inject
    public UserDetailsPresenter(UseCaseHandler useCaseHandler) {
        this.mUsecaseHandler = useCaseHandler;
    }

    @Override
    public void attachView(BaseView baseView) {
        mUserDetailsView = (UserContract.UserDetailsView) baseView;
        mUserDetailsView.setPresenter(this);
    }

    @Override
    public void getUserDetails() {
        mUsecaseHandler.execute(fetchUserData, null,
                new UseCase.UseCaseCallback<FetchUserDetails.ResponseValue>() {
                    @Override
                    public void onSuccess(FetchUserDetails.ResponseValue response) {
                        if (!response.getUserDetails().getName().equals(""))
                            mUserDetailsView.showUserDetails(response.getUserDetails());
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
    }

    @Override
    public void verifyPan(String number) {
        mUsecaseHandler.execute(verifyPanDetails, new VerifyPanDetails.RequestValues(number),
                new UseCase.UseCaseCallback<VerifyPanDetails.ResponseValue>() {
                    @Override
                    public void onSuccess(VerifyPanDetails.ResponseValue response) {
                        mUserDetailsView.showPanStatus(response.isStatus());
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
    }
}
