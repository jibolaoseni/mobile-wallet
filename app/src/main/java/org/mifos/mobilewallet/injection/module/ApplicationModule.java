package org.mifos.mobilewallet.injection.module;

import android.app.Application;
import android.content.Context;

import org.mifos.mobilewallet.core.UseCaseHandler;
import org.mifos.mobilewallet.core.UseCaseThreadPoolScheduler;
import org.mifos.mobilewallet.data.fineract.api.FineractApiManager;
import org.mifos.mobilewallet.data.local.PreferencesHelper;
import org.mifos.mobilewallet.data.pixiepay.api.PixiePayApiManager;
import org.mifos.mobilewallet.data.rbl.api.RblApiManager;
import org.mifos.mobilewallet.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    UseCaseHandler provideUsecaseHandler() {
        return new UseCaseHandler(new UseCaseThreadPoolScheduler());
    }

    @Provides
    @Singleton
    FineractApiManager provideFineractApiManager() {
        return new FineractApiManager();
    }

    @Provides
    @Singleton
    RblApiManager provideRblApiManager() {
        return new RblApiManager();
    }

    @Provides
    @Singleton
    PixiePayApiManager providePixiePayApiManager() {
        return new PixiePayApiManager();
    }

    @Provides
    @Singleton
    PreferencesHelper providePrefManager(@ApplicationContext Context context) {
        return new PreferencesHelper(context);
    }

}