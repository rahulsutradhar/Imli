package com.imli.demo.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.imli.demo.BR;

/**
 * Created by developers on 16/04/16.
 */
public class ViewModelSpinnerItem extends BaseObservable {

    private String groupName;


    @Bindable
    public String getGroupName() {
        return groupName.toString();
    }

    public void setGroupName(String groupName) {

        this.groupName = groupName.toString();
        notifyPropertyChanged(BR.groupName);
    }

}
