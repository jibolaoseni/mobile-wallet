package org.mifos.mobilewallet.account;

import org.mifos.mobilewallet.account.domain.model.Account;
import org.mifos.mobilewallet.core.BasePresenter;
import org.mifos.mobilewallet.core.BaseView;

import java.util.List;

/**
 * Created by naman on 11/7/17.
 */

public interface AccountContract {

    interface AccountsView extends BaseView<AccountsPresenter> {

        void showAccounts(List<Account> accounts);
        void showError(String message);
    }

    interface AccountsPresenter extends BasePresenter {

        void fetchAccounts();
    }
}
