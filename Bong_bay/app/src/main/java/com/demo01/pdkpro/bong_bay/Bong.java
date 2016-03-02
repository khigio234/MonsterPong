package com.demo01.pdkpro.bong_bay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Bong extends AppCompatActivity {

    //MainView mainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new MyVew(this));
        //View bouncingBallView = new BouncingBallView(this);
        //mainView = new MainView(this);
        setContentView(new MainView(this));
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
}
