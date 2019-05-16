package com.edsusantoo.bismillah.moviecatalogue.data;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.edsusantoo.bismillah.moviecatalogue.data.network.ApiServices;
import com.edsusantoo.bismillah.moviecatalogue.data.network.RetrofitConfig;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.movie.MovieResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.network.model.tvshow.TvShowResponse;
import com.edsusantoo.bismillah.moviecatalogue.data.pref.SharedPref;
import com.edsusantoo.bismillah.moviecatalogue.utils.Constant;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class MoviesRepository implements Repository {
    private ApiServices apiServices;
    private CompositeDisposable compositeDisposable;
    private SharedPref pref;
    private MutableLiveData<TvShowResponse> dataTvShows = new MutableLiveData<>();

    public MoviesRepository(Context context) {
        apiServices = RetrofitConfig.getApiServices();
        compositeDisposable = new CompositeDisposable();
        pref = new SharedPref(context);
    }


    @Override
    public Observable<MovieResponse> getMovie(String api_key, String language) {
        return apiServices.getMovie(api_key, language);
    }

    @Override
    public Observable<TvShowResponse> getTvShow(String api_key, String language) {
        return apiServices.getTvMovie(api_key, language);
    }

    @Override
    public String getLanguage() {
        return pref.getSharedPref().getString(Constant.PREF_LANGUAGE, "");
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
