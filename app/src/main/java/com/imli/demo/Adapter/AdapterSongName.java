package com.imli.demo.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imli.demo.BR;
import com.imli.demo.R;
import com.imli.demo.ViewModels.ViewModelSongName;
import com.imli.demo.ViewModels.ViewModelSongNumberIndex;
import com.imli.demo.databinding.ItemSongNameBinding;

/**
 * Created by developers on 19/04/16.
 */
public class AdapterSongName extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ObservableArrayList<ViewModelSongName> listName;


    LayoutInflater inflater;
    Context context;

    public AdapterSongName(Context context,ObservableArrayList<ViewModelSongName> listName) {
        inflater = LayoutInflater.from(context);
        this.context = context;

        this.listName = listName;
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View view;

       // view = inflater.inflate(R.layout.item_song_name, parent, false);
        //vh = new BindingHolderSongName(view);
        ItemSongNameBinding mbinding = DataBindingUtil.inflate(inflater,R.layout.item_song_name,parent,false);
        vh = new BindingHolderSongName(mbinding.getRoot());

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final ViewModelSongName name = listName.get(position);

        //binding the the song name data
        ((BindingHolderSongName) holder).getBinding().setVariable(BR.song, name);
        ((BindingHolderSongName) holder).getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return listName.size();
    }

    public static class BindingHolderSongName extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolderSongName(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);

        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

}
