package com.imli.demo.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.widget.Spinner;

import com.imli.demo.Adapter.CustomSpinnerAdapterGroupName;
import com.imli.demo.Adapter.CustomSpinnerAdpaterSongNumber;
import com.imli.demo.BR;

/**
 * Created by developers on 21/04/16.
 */
public class ViewModelSpinner extends BaseObservable {


    private ObservableArrayList<ViewModelGroupNameSpinnerItem> list;
    private ObservableArrayList<ViewModelSongNumberSpinnerItem> numberList;

    @Bindable
    public ObservableArrayList<ViewModelGroupNameSpinnerItem> getList() {
        return list;
    }

    public void setList(ObservableArrayList<ViewModelGroupNameSpinnerItem> list) {
        this.list = list;
        notifyPropertyChanged(BR.list);
    }

    @BindingAdapter("app:items")
    public static void setItems(Spinner spinner, final ObservableArrayList<ViewModelGroupNameSpinnerItem> list) {
        CustomSpinnerAdapterGroupName customSpinnerAdapter = new CustomSpinnerAdapterGroupName(spinner.getContext(), list);
        spinner.setAdapter(customSpinnerAdapter);


    }


    //second spinner

    @Bindable
    public ObservableArrayList<ViewModelSongNumberSpinnerItem> getNumberList() {
        return numberList;
    }

    public void setNumberList(ObservableArrayList<ViewModelSongNumberSpinnerItem> numberList) {
        this.numberList = numberList;
    }


    @BindingAdapter("app:spinnerAdapter")
    public static void setSpinnerAdapter(Spinner spinner, final ObservableArrayList<ViewModelSongNumberSpinnerItem> list) {
        CustomSpinnerAdpaterSongNumber customSpinnerAdpaterSongNumber = new CustomSpinnerAdpaterSongNumber(spinner.getContext(), list);
        spinner.setAdapter(customSpinnerAdpaterSongNumber);


    }


}
