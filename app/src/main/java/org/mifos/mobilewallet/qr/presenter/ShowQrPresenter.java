package org.mifos.mobilewallet.qr.presenter;

import org.mifos.mobilewallet.core.BaseView;
import org.mifos.mobilewallet.core.UseCase;
import org.mifos.mobilewallet.core.UseCaseHandler;
import org.mifos.mobilewallet.qr.QrContract;
import org.mifos.mobilewallet.qr.domain.usecase.GenerateQr;

import javax.inject.Inject;

/**
 * Created by naman on 8/7/17.
 */

public class ShowQrPresenter implements QrContract.ShowQrPresenter {

    private QrContract.ShowQrView mShowQrView;
    private final UseCaseHandler mUsecaseHandler;

    @Inject
    GenerateQr generateQr;

    @Inject
    public ShowQrPresenter(UseCaseHandler useCaseHandler) {
        this.mUsecaseHandler = useCaseHandler;
    }

    @Override
    public void attachView(BaseView baseView) {
        this.mShowQrView = (QrContract.ShowQrView) baseView;
        mShowQrView.setPresenter(this);

    }

    @Override
    public void generateQr(String data) {

        mUsecaseHandler.execute(generateQr, new GenerateQr.RequestValues(data), new UseCase.UseCaseCallback<GenerateQr.ResponseValue>() {
            @Override
            public void onSuccess(GenerateQr.ResponseValue response) {
                mShowQrView.showGeneratedQr(response.getBitmap());
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
