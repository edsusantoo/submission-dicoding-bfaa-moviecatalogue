package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class MenuBottomViewModel extends ViewModel {
    private MutableLiveData<String> menuBottom = new MutableLiveData<>();

    public MenuBottomViewModel() {

    }

    LiveData<String> getPositionMenuBottom() {
        return menuBottom;
    }

    void setPositionMenuBottom(String TAG) {
        menuBottom.postValue(TAG);
    }
}
