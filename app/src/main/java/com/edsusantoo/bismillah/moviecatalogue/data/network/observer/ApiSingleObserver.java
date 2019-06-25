package com.edsusantoo.bismillah.moviecatalogue.data.network.observer;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;

public abstract class ApiSingleObserver<T> implements SingleObserver<T> {

    private CompositeDisposable compositeDisposable;

    protected ApiSingleObserver(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }


    public abstract void onSuccessful(T response);

    public abstract void onFailure(String message);

    //public abstract void onFinish();


    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        try {
            if (e instanceof CompositeException) {
                onFailure(e.getMessage());
            } else if (e instanceof UnknownHostException) {
                onFailure(e.getMessage());
            } else if (e instanceof SocketTimeoutException) {
                onFailure(e.getMessage());
            } else if (e instanceof SSLHandshakeException) {
                onFailure("Kami tidak dapat memvalidasi koneksi anda, pastikan anda tidak menggunakan proxy pada wifi dan gunakan data seluler. Jika masalah ini terus berlanjut, hubungi CS kami.");
            } else if (e instanceof IOException) {
                onFailure(e.getMessage());
            } else if (e instanceof Exception) {
                onFailure(e.getMessage());
            } else {
                onFailure(e.getMessage());
            }
            //onFinish();
        } catch (Exception ex) {
            onFailure(ex.getMessage());
            //onFinish();
        }
    }

    @Override
    public void onSuccess(T t) {
        onSuccessful(t);
    }
}
