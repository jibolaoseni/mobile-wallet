package org.mifos.mobilewallet.core;

/**
 * Interface for schedulers, see {@link UseCaseThreadPoolScheduler}.
 */
public interface UseCaseScheduler {

    void execute(Runnable runnable);

    <V extends UseCase.ResponseValue> void notifyResponse(final V response,
                                                          final UseCase.UseCaseCallback<V>
                                                                  useCaseCallback);

    <V extends UseCase.ResponseValue> void onError(final String message,
            final UseCase.UseCaseCallback<V> useCaseCallback);
}
