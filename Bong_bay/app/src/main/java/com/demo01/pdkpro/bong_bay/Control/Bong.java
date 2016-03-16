package com.demo01.pdkpro.bong_bay.Control;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ViewFlipper;

import com.demo01.pdkpro.bong_bay.Game.MainGame;
import com.demo01.pdkpro.bong_bay.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Bong extends AppCompatActivity {

    // Tạo đối tượng ViewFlipper
    private ViewFlipper viewFlipper;
    private View viewMenu;
    HashMap<Integer,ArrayList<Integer>> hashBacground; //chua giao dien cac component
    ArrayList<Integer> arrMenu ;
    int index = 1;//so de lay trong hash map
    MainGame mainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //tao cac gia tri
        //createarrMenu();
        //createHash();
        //set view
        setContentView(new MainGame(this));
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);

        viewMenu = (View) findViewById(R.id.menu);
//        viewMenu.setBackgroundResource(R.drawable.menu01);
        //
        //mainView = new MainView(viewMenu.getContext(),hashBacground.get(arrMenu.get(0)));
//        viewFlipper.addView(mainView);
/*
        viewMenu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if(x>245 && x < 528 && y >1020 && y< 1122){
//                            setContentView(new MainView(viewMenu.getContext(),hashBacground.get(arrMenu.get(index-1))));
                            //mainView = new MainView(viewMenu.getContext(),hashBacground.get(arrMenu.get(0)));
                            mainView.setArrGUI(hashBacground.get(arrMenu.get(index - 1)));
                            mainView.setViewFlipper(viewFlipper);
                            viewFlipper.setDisplayedChild(1);
                        }
                        else if(x>18 && x < 102 && y >311 && y< 376){
                            index--;
                            if(index<1){index = 5;};
                            viewMenu.setBackgroundResource(arrMenu.get(index-1));
                        }else if (x>661 && x < 748 && y >311 && y< 376){
                            index++;
                            if(index>5){index = 1;};
                            viewMenu.setBackgroundResource(arrMenu.get(index - 1));
                        }
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //this.mainView.
    }

    private void createHash(){
        this.hashBacground = new HashMap<>();
        //man 1
        ArrayList<Integer> arr1 = new ArrayList<>();
        arr1.add(R.drawable.map1);
        arr1.add(R.drawable.ball1);
        arr1.add(R.drawable.block1);
        hashBacground.put(R.drawable.menu01,arr1);

        //man 2
        ArrayList<Integer> arr2 = new ArrayList<>();
        arr2.add(R.drawable.map2);
        arr2.add(R.drawable.ball2);
        arr2.add(R.drawable.block1);
        hashBacground.put(R.drawable.menu02,arr2);

        //man 3
        ArrayList<Integer> arr3 = new ArrayList<>();
        arr3.add(R.drawable.map3);
        arr3.add(R.drawable.ball3);
        arr3.add(R.drawable.block2);
        hashBacground.put(R.drawable.menu03,arr3);

        //man 4
        ArrayList<Integer> arr4 = new ArrayList<>();
        arr4.add(R.drawable.map4);
        arr4.add(R.drawable.ball4);
        arr4.add(R.drawable.block2);
        hashBacground.put(R.drawable.menu04,arr4);

        //man 5
        ArrayList<Integer> arr5 = new ArrayList<>();
        arr5.add(R.drawable.map5);
        arr5.add(R.drawable.ball5);
        arr5.add(R.drawable.block3);
        hashBacground.put(R.drawable.menu05,arr5);
    }
    private void createarrMenu(){
        arrMenu = new ArrayList<>();
        arrMenu.add(R.drawable.menu01);
        arrMenu.add(R.drawable.menu02);
        arrMenu.add(R.drawable.menu03);
        arrMenu.add(R.drawable.menu04);
        arrMenu.add(R.drawable.menu05);
    }*/
}}
