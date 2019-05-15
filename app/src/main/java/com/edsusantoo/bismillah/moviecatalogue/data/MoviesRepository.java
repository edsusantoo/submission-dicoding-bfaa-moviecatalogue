package com.edsusantoo.bismillah.moviecatalogue.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.edsusantoo.bismillah.moviecatalogue.data.network.ApiObserver;
import com.edsusantoo.bismillah.moviecatalogue.data.network.ApiServices;
import com.edsusantoo.bismillah.moviecatalogue.data.network.RetrofitConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.pref.SharedPref;
import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MoviesRepository implements Repository {
    private ApiServices apiServices;
    private CompositeDisposable compositeDisposable;
    private SharedPref pref;
    private MutableLiveData<MovieResponse> dataMovies = new MutableLiveData<>();

    public MoviesRepository(Context context) {
        apiServices = RetrofitConfig.getApiServices();
        compositeDisposable = new CompositeDisposable();
        pref = new SharedPref(context);
    }


    @Override
    public LiveData<MovieResponse> getMovie(String api_key, String language) {

        apiServices.getMovie(api_key, language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiObserver<MovieResponse>(compositeDisposable) {
                    @Override
                    public void onSuccess(MovieResponse response) {
                        dataMovies.setValue(response);
                    }

                    @Override
                    public void onFailure(String message) {
                        dataMovies.setValue(null);
                    }
                });

        return dataMovies;
    }

    @Override
    public String getLanguage() {
        return pref.getSharedPref().getString(Constant.PREF_LANGUAGE, "");
    }
}
