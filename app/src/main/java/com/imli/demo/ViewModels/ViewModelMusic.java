package com.imli.demo.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.imli.demo.Adapter.AdapterSongName;
import com.imli.demo.BR;


/**
 * Created by developers on 19/04/16.
 */
public class ViewModelMusic extends BaseObservable {

    private String artistName;
    private String albumName;
    private ObservableArrayList<ViewModelSongName> songList;

    @Bindable
    public String getArtistName() {
        return artistName;
    }

    @Bindable
    public String getAlbumName() {
        return albumName;
    }

    @Bindable
    public ObservableArrayList<ViewModelSongName> getSongList() {
        return songList;
    }


    public void setArtistName(String artistName) {
        this.artistName = artistName;
        notifyPropertyChanged(BR.artistName);
    }


    public void setAlbumName(String albumName) {
        this.albumName = albumName;
        notifyPropertyChanged(BR.albumName);
    }


    public void setSongList(ObservableArrayList<ViewModelSongName> songList) {
        this.songList = songList;
        notifyPropertyChanged(BR.songList);

    }

    @BindingAdapter({"tool:adapter","tool:spanCount"})
    public static void setAdapter(RecyclerView recyclerView,ObservableArrayList<ViewModelSongName> list,int spanCount)
    {

        if(spanCount > list.size() )
        {
            spanCount = list.size();
        }
        GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(),spanCount,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.hasFixedSize();
        AdapterSongName adapter = new AdapterSongName(recyclerView.getContext(),list);
        recyclerView.setAdapter(adapter);
    }

}
