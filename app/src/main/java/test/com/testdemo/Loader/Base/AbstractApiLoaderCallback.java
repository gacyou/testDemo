package test.com.testdemo.Loader.Base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;

import test.com.testdemo.Api.Base.ApiException;
import test.com.testdemo.Api.Base.ApiResult;

public abstract class AbstractApiLoaderCallback<T> implements LoaderManager.LoaderCallbacks<ApiResult<T>> {
    private static final int SC_FORBIDDEN = 403;
    private final int loadId = genLoaderId();
    private LoaderManager loaderManager;

    public AbstractApiLoaderCallback() {
    }

    @SuppressLint("WrongConstant")
    public final void onLoadFinished(Loader<ApiResult<T>> loader, ApiResult<T> result) {
        Context context = loader.getContext();
        if (this.loaderManager != null) {
            this.loaderManager.destroyLoader(loader.getId());
        }

        this.onPreLoadFinished();
        if (result.getError() != null) {
            if (!this.onApiError(result.getError()) && result.getError().getStatus() != 403) {
                try {

                } catch (Exception var7) {
                    int status = result.getError().getStatus();
                    String message = result.getError().getReason();

                }
            }

            if (this.isForbidden(result)) {
                this.handleForbidden(context);
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                intent.addFlags(32768);
                intent.addFlags(268435456);
                context.startActivity(intent);
            }
        } else if (result.getException() != null) {
            if (!this.onException(result.getException())) {

            }
        } else {
            this.onStatusOk(result.getData());
        }

        this.onPostLoadFinished();
    }

    public void onLoaderReset(Loader<ApiResult<T>> loader) {
        this.loaderManager = null;
    }

    protected void onPreLoadFinished() {
    }

    protected abstract void onStatusOk(T var1);

    protected boolean onApiError(ApiException error) {
        return false;
    }

    protected boolean onException(Exception e) {
        return false;
    }

    protected void onPostLoadFinished() {
    }

    private void setLoaderManager(LoaderManager loaderManager) {
        this.loaderManager = loaderManager;
    }

    protected abstract String toErrorMessage(ApiResult<T> var1);

    protected boolean isForbidden(ApiResult<T> result) {
        return result.getError().getStatus() == 403;
    }

    protected abstract void handleForbidden(Context var1);

    private static int genLoaderId() {
        return (int)(Math.random() * 20000.0D) + 10000;
    }

    public static void startApiLoader(LoaderManager loaderManager, AbstractApiLoaderCallback<?> callback) {
        startApiLoader((LoaderManager)loaderManager, callback, (Bundle)null, true);
    }

    public static void startApiLoader(Activity activity, AbstractApiLoaderCallback<?> callback) {
        startApiLoader((LoaderManager)activity.getLoaderManager(), callback, (Bundle)null, true);
    }

    public static void startApiLoader(Activity activity, AbstractApiLoaderCallback<?> callback, Bundle args) {
        startApiLoader(activity.getLoaderManager(), callback, args, true);
    }

    public static void startApiLoader(Activity activity, AbstractApiLoaderCallback<?> callback, Bundle args, boolean singleton) {
        startApiLoader(activity.getLoaderManager(), callback, args, singleton);
    }

    private static void startApiLoader(LoaderManager loaderManager, AbstractApiLoaderCallback<?> callback, Bundle args, boolean singleton) {
        callback.setLoaderManager(loaderManager);
        loaderManager.restartLoader(singleton ? callback.loadId : genLoaderId(), args, callback);
    }
}
