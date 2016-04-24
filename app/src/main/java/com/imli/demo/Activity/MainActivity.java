package com.imli.demo.Activity;


import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.imli.demo.ViewModels.ViewModelSongNumberSpinnerItem;
import com.imli.demo.ViewModels.ViewModelSongNumberIndex;
import com.imli.demo.ViewModels.ViewModelSpinner;
import com.imli.demo.R;
import com.imli.demo.ViewModels.ViewModelGroupNameSpinnerItem;
import com.imli.demo.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    ObservableArrayList<ViewModelGroupNameSpinnerItem> groupList = new ObservableArrayList<>();
    ObservableArrayList<ViewModelSongNumberSpinnerItem> numberList = new ObservableArrayList<>();

    public FragmentCommunicator fragmentCommunicator;

    //interface for communicating with the fragment, because spinner is in activity  and recyclerview is in fragment
    //need to pass the spnner choosen value to the fragment , so that accordingly data can be shown
    public interface FragmentCommunicator{
        public void passDataToFragmentSpinnerGroup(int someValue,ViewModelSongNumberIndex index);
        public void passDataToFragmentSongNumber(int someValue);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(" ");

        //add item in spinner for the Group of Song
        add();
        //add item in spinner for the song number in a page
        addSongNumber();

        //binding data
        ViewModelSpinner modelSpinner = new ViewModelSpinner();
        modelSpinner.setList(groupList);
        modelSpinner.setNumberList(numberList);
        binding.setViewModelSpinner(modelSpinner);

        //listener for the spinner Group to get the selected data
        binding.spinnerSongGroupBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (fragmentCommunicator != null)
                {
                    ViewModelSongNumberSpinnerItem indexSong = (ViewModelSongNumberSpinnerItem) binding.spinnerSongNumber.getSelectedItem();
                    ViewModelSongNumberIndex  index = new ViewModelSongNumberIndex();
                    index.setIndexSongNumber(Integer.parseInt(indexSong.getSongNumber()));
                    fragmentCommunicator.passDataToFragmentSpinnerGroup(position,index);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //listener for the spinner songNumber to get the selected song Number to be displayed in a page
        binding.spinnerSongNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               if(fragmentCommunicator != null) {
                   fragmentCommunicator.passDataToFragmentSongNumber(position+1);
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void add() {
        ViewModelGroupNameSpinnerItem m1 = new ViewModelGroupNameSpinnerItem();
        m1.setGroupName("Album");
        groupList.add(m1);

        ViewModelGroupNameSpinnerItem m2 = new ViewModelGroupNameSpinnerItem();
        m2.setGroupName("Artist");
        groupList.add(m2);

    }

    public void addSongNumber() {
        ViewModelSongNumberSpinnerItem m1 = new ViewModelSongNumberSpinnerItem();
        m1.setSongNumber("1");
        numberList.add(m1);

        ViewModelSongNumberSpinnerItem m2 = new ViewModelSongNumberSpinnerItem();
        m2.setSongNumber("2");
        numberList.add(m2);

        ViewModelSongNumberSpinnerItem m3 = new ViewModelSongNumberSpinnerItem();
        m3.setSongNumber("3");
        numberList.add(m3);

        ViewModelSongNumberSpinnerItem m4 = new ViewModelSongNumberSpinnerItem();
        m4.setSongNumber("4");
        numberList.add(m4);

        ViewModelSongNumberSpinnerItem m5 = new ViewModelSongNumberSpinnerItem();
        m5.setSongNumber("5");
        numberList.add(m5);


    }
}
