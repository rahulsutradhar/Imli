package com.imli.demo.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.imli.demo.BR;

/**
 * Created by developers on 22/04/16.
 */
public class ViewModelSongNumber extends BaseObservable {

    private String songNumber;
    private int index;

    @Bindable
    public String getSongNumber() {
        return songNumber;
    }

    public void setSongNumber(String songNumber) {
        this.songNumber = songNumber;
        notifyPropertyChanged(BR.songNumber);
    }

}
