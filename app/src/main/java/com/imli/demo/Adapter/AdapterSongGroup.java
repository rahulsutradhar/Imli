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
import com.imli.demo.ViewModels.ViewModelMusic;
import com.imli.demo.ViewModels.ViewModelSongNumberIndex;
import com.imli.demo.databinding.ItemSongGroupNameBinding;

/**
 * Created by developers on 19/04/16.
 */
public class AdapterSongGroup extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ObservableArrayList<ViewModelMusic> listMusic;
    private ViewModelSongNumberIndex viewModelSongNumberIndex;


    LayoutInflater inflater;
    Context context;

    public AdapterSongGroup(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setMusicData(ObservableArrayList<ViewModelMusic> listMusic,ViewModelSongNumberIndex viewModelSongNumberIndex) {

        //the songNumber data to be displayed in a page
        this.viewModelSongNumberIndex = viewModelSongNumberIndex;
        //the list of the data
        this.listMusic = listMusic;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder vh;

        ItemSongGroupNameBinding mbinding = DataBindingUtil.inflate(inflater, R.layout.item_song_group_name, parent, false);
        vh = new BindingHolderMusicType(mbinding.getRoot());

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (listMusic.size() > 0) {
            final ViewModelMusic viewModelMusic = listMusic.get(position);
            //binding the list of data with group name and song list
            ((BindingHolderMusicType) holder).getBinding().setVariable(BR.music, viewModelMusic);

            //binding the song number data
            ((BindingHolderMusicType) holder).getBinding().setVariable(BR.songNumber,viewModelSongNumberIndex);
            ((BindingHolderMusicType) holder).getBinding().executePendingBindings();

        }

    }

    @Override
    public int getItemCount() {

        return listMusic.size();
    }

    public static class BindingHolderMusicType extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolderMusicType(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);


        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

}
