package org.mifos.mobilewallet.data.fineract.api;

import org.mifos.mobilewallet.data.fineract.api.services.AuthenticationService;
import org.mifos.mobilewallet.data.fineract.api.services.ClientService;
import org.mifos.mobilewallet.data.fineract.api.services.SavingAccountsListService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by naman on 17/6/17.
 */

public class FineractApiManager {

    private static BaseURL baseUrl = new BaseURL();
    private static final String BASE_URL = baseUrl.getUrl();

    private static Retrofit retrofit;
    private static AuthenticationService authenticationApi;
    private static ClientService clientsApi;
    private static SavingAccountsListService savingAccountsListApi;

    public FineractApiManager() {
        String authToken = "";
        createService(authToken);
    }

    private static void init() {
        authenticationApi = createApi(AuthenticationService.class);
        clientsApi = createApi(ClientService.class);
        savingAccountsListApi = createApi(SavingAccountsListService.class);
    }

    private static <T> T createApi(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    public static void createService(String authToken) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new ApiInterceptor(authToken))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        init();
    }

    public AuthenticationService getAuthenticationApi() {
        return authenticationApi;
    }

    public ClientService getClientsApi() {
        return clientsApi;
    }

    public SavingAccountsListService getSavingAccountsListApi() {
        return savingAccountsListApi;
    }

}
