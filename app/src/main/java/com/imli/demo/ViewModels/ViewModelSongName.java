package com.imli.demo.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.imli.demo.BR;

/**
 * Created by developers on 19/04/16.
 */
public class ViewModelSongName extends BaseObservable {

    private String songName;

    @Bindable
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
        notifyPropertyChanged(BR.song);
    }
}
