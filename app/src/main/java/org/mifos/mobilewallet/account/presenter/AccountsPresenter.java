package org.mifos.mobilewallet.account.presenter;

import org.mifos.mobilewallet.account.AccountContract;
import org.mifos.mobilewallet.account.domain.usecase.FetchAccounts;
import org.mifos.mobilewallet.core.BaseView;
import org.mifos.mobilewallet.core.UseCase;
import org.mifos.mobilewallet.core.UseCaseHandler;

import javax.inject.Inject;

/**
 * Created by naman on 11/7/17.
 */

public class AccountsPresenter implements AccountContract.AccountsPresenter {

    private AccountContract.AccountsView maccountsView;
    private final UseCaseHandler mUsecaseHandler;

    @Inject
    FetchAccounts fetchAccounts;

    @Inject
    public AccountsPresenter(UseCaseHandler useCaseHandler) {
        this.mUsecaseHandler = useCaseHandler;
    }

    @Override
    public void attachView(BaseView baseView) {
        maccountsView = (AccountContract.AccountsView) baseView;
        maccountsView.setPresenter(this);
    }

    @Override
    public void fetchAccounts() {

        mUsecaseHandler.execute(fetchAccounts, null, new UseCase.UseCaseCallback<FetchAccounts.ResponseValue>() {
            @Override
            public void onSuccess(FetchAccounts.ResponseValue response) {
                maccountsView.showAccounts(response.getAccountList());
            }

            @Override
            public void onError(String message) {
                maccountsView.showError(message);
            }
        });
    }
}
