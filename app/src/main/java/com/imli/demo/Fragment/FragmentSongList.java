package com.imli.demo.Fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imli.demo.Activity.MainActivity;
import com.imli.demo.Adapter.AdapterSongGroup;
import com.imli.demo.R;
import com.imli.demo.Utilities.CSVFile;
import com.imli.demo.ViewModels.CSVFileGetterSetter;
import com.imli.demo.ViewModels.ViewModelMusic;
import com.imli.demo.ViewModels.ViewModelSongName;
import com.imli.demo.ViewModels.ViewModelSongNumberIndex;
import com.imli.demo.databinding.FragmentSongListBinding;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSongList extends Fragment implements MainActivity.FragmentCommunicator {

    private FragmentSongListBinding mBinding;

    private AdapterSongGroup adapter;
    ObservableArrayList<ViewModelMusic> listGroup;

    public FragmentSongList() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Context context = getActivity();
        ((MainActivity) context).fragmentCommunicator = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_song_list, container, false);


        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //get the refferance of the recyclerview android
        adapter = new AdapterSongGroup(getActivity());
        mBinding.recyclerSongGroup.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.recyclerSongGroup.setAdapter(adapter);

        SharedPreferences loadingData = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean loadAlbum = loadingData.getBoolean("ablum_data", false);
        boolean loadArtist = loadingData.getBoolean("artist_data", false);

        if (loadAlbum == true) {
            listGroup = retrieveDataFromLocalStorageAlbum();

        } else if (loadArtist == true) {
            listGroup = retrieveDataFromLocalStorageArtist();
        } else {
            //load data from CSV file
            ArrayList<CSVFileGetterSetter> list = loadCSVFile();
            listGroup = readAlbumWise(list);
            readArtistWise(list);
        }

        ViewModelSongNumberIndex indexModel = new ViewModelSongNumberIndex();
        indexModel.setIndexSongNumber(1);

        adapter.setMusicData(listGroup,indexModel);


    }


    public ArrayList<CSVFileGetterSetter> loadCSVFile() {
        InputStream inputStream = getResources().openRawResource(R.raw.sample_music_data);
        CSVFile csvFile = new CSVFile(inputStream);
        ArrayList<CSVFileGetterSetter> list = csvFile.readFile();


        return list;

    }

    public void readArtistWise(ArrayList<CSVFileGetterSetter> list) {


        int i = 0, k = 0;
        ObservableArrayList<ViewModelMusic> listGroup = new ObservableArrayList<>();

        while (i < list.size()) {
            //create object of the observable list for the song
            ObservableArrayList<ViewModelSongName> songList = new ObservableArrayList<>();

            if (list.get(i).getArtistTaken() == 0) {
                CSVFileGetterSetter object = list.get(i);
                Log.i("Consider Artist Name = ", object.getArtistName());

                k = i + 1;

                while (k < list.size()) {
                    CSVFileGetterSetter newObj = list.get(k);

                    if (newObj.getArtistTaken() == 0 && newObj.getArtistName().equalsIgnoreCase(object.getArtistName()) == true) {

                        ViewModelSongName viewModelSongName = new ViewModelSongName();
                        viewModelSongName.setSongName(newObj.getSongName());
                        songList.add(viewModelSongName);

                        list.get(k).setArtistTaken(1);
                        k++;
                    } else {
                        k++;
                    }

                }

                //get the first song name
                ViewModelSongName viewModelSongName = new ViewModelSongName();
                viewModelSongName.setSongName(object.getSongName());
                songList.add(0, viewModelSongName);

                //now store the first value
                ViewModelMusic viewModelMusic = new ViewModelMusic();
                viewModelMusic.setArtistName(object.getArtistName());
                viewModelMusic.setSongList(songList);
                listGroup.add(viewModelMusic);

                list.get(i).setArtistTaken(1);
                i++;
            } else {
                i++;
            }


        }


        //check the array data
        for (int j = 0; j < listGroup.size(); j++) {
            Log.i("Artst Name == ", listGroup.get(j).getArtistName());
            for (int m = 0; m < listGroup.get(j).getSongList().size(); m++)
                Log.i("Song Name == ", listGroup.get(j).getSongList().get(m).getSongName());
        }

        //save in local
        SharedPreferences saveAlbum = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = saveAlbum.edit();
        editor.putBoolean("artist_data", true);
        editor.commit();

        saveDataInLocalStorageArtist(listGroup);


    }

    public ObservableArrayList<ViewModelMusic> readAlbumWise(ArrayList<CSVFileGetterSetter> list) {

        int i = 0, k = 0;
        ObservableArrayList<ViewModelMusic> listGroup = new ObservableArrayList<>();

        while (i < list.size()) {
            //create object of the observable list for the song
            ObservableArrayList<ViewModelSongName> songList = new ObservableArrayList<>();

            if (list.get(i).getAlbumTaken() == 0) {
                CSVFileGetterSetter object = list.get(i);
                Log.i("Consider Album Name = ", object.getAlbumName());

                k = i + 1;

                while (k < list.size()) {
                    CSVFileGetterSetter newObj = list.get(k);

                    if (newObj.getAlbumTaken() == 0 && newObj.getAlbumName().equalsIgnoreCase(object.getAlbumName()) == true) {

                        ViewModelSongName viewModelSongName = new ViewModelSongName();
                        viewModelSongName.setSongName(newObj.getSongName());
                        songList.add(viewModelSongName);
                        Log.i("Got Song name = ", newObj.getSongName());
                        Log.i("Position Deleted = ", k + "");

                        list.get(k).setAlbumTaken(1);
                        k++;
                    } else {
                        k++;
                    }

                }

                //get the first song name
                ViewModelSongName viewModelSongName = new ViewModelSongName();
                viewModelSongName.setSongName(object.getSongName());
                songList.add(0, viewModelSongName);

                //now store the first value
                ViewModelMusic viewModelMusic = new ViewModelMusic();
                viewModelMusic.setAlbumName(object.getAlbumName());
                viewModelMusic.setSongList(songList);
                listGroup.add(viewModelMusic);

                list.get(i).setAlbumTaken(1);
                i++;
            } else {
                i++;
            }


        }


        //check the array data
        for (int j = 0; j < listGroup.size(); j++) {
            Log.i("Album Name == ", listGroup.get(j).getAlbumName());
            for (int m = 0; m < listGroup.get(j).getSongList().size(); m++)
                Log.i("Song Name == ", listGroup.get(j).getSongList().get(m).getSongName());
        }

        //save in local
        SharedPreferences saveAlbum = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = saveAlbum.edit();
        editor.putBoolean("album_data", true);
        editor.commit();

        saveDataInLocalStorageAlbum(listGroup);


        return listGroup;
    }

    public void saveDataInLocalStorageArtist(ObservableArrayList<ViewModelMusic> arrayListArtist) {


        SharedPreferences data = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = data.edit();
        Gson gson = new Gson();
        String listArtist = gson.toJson(arrayListArtist);
        editor.putString("artist_song_data", listArtist);
        editor.commit();
    }

    public ObservableArrayList<ViewModelMusic> retrieveDataFromLocalStorageArtist() {
        SharedPreferences homeStoresData = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        Gson gson = new Gson();
        String listArtist = homeStoresData.getString("artist_song_data", "");

        Type type = new TypeToken<ObservableArrayList<ViewModelMusic>>() {
        }.getType();
        ObservableArrayList<ViewModelMusic> list = gson.fromJson(listArtist, type);

        return list;
    }


    public void saveDataInLocalStorageAlbum(ObservableArrayList<ViewModelMusic> arrayListAlbum) {


        SharedPreferences data = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = data.edit();
        Gson gson = new Gson();
        String listAlbum = gson.toJson(arrayListAlbum);
        editor.putString("album_song_data", listAlbum);
        editor.commit();
    }

    public ObservableArrayList<ViewModelMusic> retrieveDataFromLocalStorageAlbum() {
        SharedPreferences homeStoresData = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        Gson gson = new Gson();
        String listAlbum = homeStoresData.getString("album_song_data", "");

        Type type = new TypeToken<ObservableArrayList<ViewModelMusic>>() {
        }.getType();
        ObservableArrayList<ViewModelMusic> list = gson.fromJson(listAlbum, type);

        return list;
    }

    @Override
    public void passDataToFragmentSpinnerGroup(int someValue,ViewModelSongNumberIndex viewModelSongNumberIndex) {

        listGroup.clear();
       // adapter.setMusicData(listGroup);

        //load dat from the
        switch (someValue)
        {
            case 0:
                listGroup = retrieveDataFromLocalStorageAlbum();
                adapter.setMusicData(listGroup,viewModelSongNumberIndex);
                break;
            case 1:
                listGroup = retrieveDataFromLocalStorageArtist();
                adapter.setMusicData(listGroup,viewModelSongNumberIndex);
                break;
        }
    }

    @Override
    public void passDataToFragmentSongNumber(int someValue) {

        ViewModelSongNumberIndex index = new ViewModelSongNumberIndex();
        index.setIndexSongNumber(someValue);
        adapter.setMusicData(listGroup,index);

    }
}
