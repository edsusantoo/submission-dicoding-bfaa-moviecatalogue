package com.edsusantoo.bismillah.moviecatalogue.ui.menubottom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


//TODO: Buat gimana caranya semua viewmodel extends nya viewmodel bukan androidviewmodel karena jangan sampai ada context diviewmodel, karena untuk mengupdate datanya si activitynya harus recreate
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
