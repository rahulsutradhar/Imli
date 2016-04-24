package com.imli.demo.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.imli.demo.R;
import com.imli.demo.ViewModels.ViewModelSpinnerItem;
import com.imli.demo.databinding.ItemRowSpinnerBinding;

import com.imli.demo.BR;

/**
 * Created by developers on 22/04/16.
 */
public class CustomSpinnerAdapter extends ArrayAdapter<ViewModelSpinnerItem> {

    private Context context1;
    private ObservableArrayList<ViewModelSpinnerItem> data;
    public Resources res;
    LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, ObservableArrayList<ViewModelSpinnerItem> objects) {
        super(context, R.layout.item_row_spinner, objects);

        context1 = context;
        data = objects;

        inflater = (LayoutInflater) context1
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
       // return super.getDropDownView(position, convertView, parent);

        return getCustomView(position,convertView,parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        return getCustomView(position,convertView,parent);

    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        // View row = inflater.inflate(R.layout.spinner_row_item, parent, false);
        //  TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);//
        //tvCategory.setText(data.get(position).toString());


        ItemRowSpinnerBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.item_row_spinner, parent, false);

        final ViewModelSpinnerItem viewModelSpinnerItem = data.get(position);
        mBinding.setVariable(BR.spinnerItem, viewModelSpinnerItem);

        return mBinding.getRoot();
    }

}
