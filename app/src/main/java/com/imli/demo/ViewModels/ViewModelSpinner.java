package com.imli.demo.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.widget.Spinner;

import com.imli.demo.Adapter.CustomSpinnerAdapter;
import com.imli.demo.Adapter.CustomSpinnerAdpaterSongNumber;
import com.imli.demo.BR;

/**
 * Created by developers on 21/04/16.
 */
public class ViewModelSpinner extends BaseObservable {


    private ObservableArrayList<ViewModelSpinnerItem> list;
    private ObservableArrayList<ViewModelSongNumber> numberList;

    @Bindable
    public ObservableArrayList<ViewModelSpinnerItem> getList() {
        return list;
    }

    public void setList(ObservableArrayList<ViewModelSpinnerItem> list) {
        this.list = list;
        notifyPropertyChanged(BR.list);
    }

    @BindingAdapter("app:items")
    public static void setItems(Spinner spinner, final ObservableArrayList<ViewModelSpinnerItem> list) {
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(spinner.getContext(), list);
        spinner.setAdapter(customSpinnerAdapter);


    }


    //second spinner

    @Bindable
    public ObservableArrayList<ViewModelSongNumber> getNumberList() {
        return numberList;
    }

    public void setNumberList(ObservableArrayList<ViewModelSongNumber> numberList) {
        this.numberList = numberList;
    }


    @BindingAdapter("app:spinnerAdapter")
    public static void setSpinnerAdapter(Spinner spinner, final ObservableArrayList<ViewModelSongNumber> list) {
        CustomSpinnerAdpaterSongNumber customSpinnerAdpaterSongNumber = new CustomSpinnerAdpaterSongNumber(spinner.getContext(), list);
        spinner.setAdapter(customSpinnerAdpaterSongNumber);


    }


}
