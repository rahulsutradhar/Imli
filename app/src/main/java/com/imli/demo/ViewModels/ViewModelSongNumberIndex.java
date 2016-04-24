package com.imli.demo.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableInt;
import com.imli.demo.BR;

/**
 * Created by developers on 22/04/16.
 */
public class ViewModelSongNumberIndex extends BaseObservable {

    private int indexSongNumber;

    @Bindable
    public int getIndexSongNumber() {
        return indexSongNumber;
    }

    public void setIndexSongNumber(int indexSongNumber) {
        this.indexSongNumber = indexSongNumber;
        notifyPropertyChanged(BR.indexSongNumber);
    }
}
