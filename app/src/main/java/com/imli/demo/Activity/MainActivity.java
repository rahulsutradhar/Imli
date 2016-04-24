package com.imli.demo.Activity;


import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.imli.demo.ViewModels.ViewModelSongNumber;
import com.imli.demo.ViewModels.ViewModelSongNumberIndex;
import com.imli.demo.ViewModels.ViewModelSpinner;
import com.imli.demo.R;
import com.imli.demo.ViewModels.ViewModelSpinnerItem;
import com.imli.demo.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    ObservableArrayList<ViewModelSpinnerItem> groupList = new ObservableArrayList<>();
    ObservableArrayList<ViewModelSongNumber> numberList = new ObservableArrayList<>();

    public FragmentCommunicator fragmentCommunicator;

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

        //add item
        add();
        //add item for 2nd spinner
        addSongNumber();

        ViewModelSpinner modelSpinner = new ViewModelSpinner();
        modelSpinner.setList(groupList);
        modelSpinner.setNumberList(numberList);
        binding.setViewModelSpinner(modelSpinner);

        binding.spinnerSongGroupBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (fragmentCommunicator != null)
                {
                    ViewModelSongNumber indexSong = (ViewModelSongNumber) binding.spinnerSongNumber.getSelectedItem();
                    ViewModelSongNumberIndex  index = new ViewModelSongNumberIndex();
                    index.setIndexSongNumber(Integer.parseInt(indexSong.getSongNumber()));
                    fragmentCommunicator.passDataToFragmentSpinnerGroup(position,index);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        ViewModelSpinnerItem m1 = new ViewModelSpinnerItem();
        m1.setGroupName("Album");
        groupList.add(m1);

        ViewModelSpinnerItem m2 = new ViewModelSpinnerItem();
        m2.setGroupName("Artist");
        groupList.add(m2);

    }

    public void addSongNumber() {
        ViewModelSongNumber m1 = new ViewModelSongNumber();
        m1.setSongNumber("1");
        numberList.add(m1);

        ViewModelSongNumber m2 = new ViewModelSongNumber();
        m2.setSongNumber("2");
        numberList.add(m2);

        ViewModelSongNumber m3 = new ViewModelSongNumber();
        m3.setSongNumber("3");
        numberList.add(m3);

        ViewModelSongNumber m4 = new ViewModelSongNumber();
        m4.setSongNumber("4");
        numberList.add(m4);

        ViewModelSongNumber m5 = new ViewModelSongNumber();
        m5.setSongNumber("5");
        numberList.add(m5);


    }
}
