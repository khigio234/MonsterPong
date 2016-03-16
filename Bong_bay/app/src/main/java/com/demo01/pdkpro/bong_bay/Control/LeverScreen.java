package com.demo01.pdkpro.bong_bay.Control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.demo01.pdkpro.bong_bay.R;

public class LeverScreen extends AppCompatActivity {

    private float xOld;
    private ViewFlipper viewFlipper;
    private ImageView imageView;
    private Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lever_screen);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        it = new Intent(LeverScreen.this,GamePlayScreen.class);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                xOld = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float xCurrent = event.getX();
                float yCurrent = event.getY();
                if(xCurrent > xOld || xCurrent>18 && xCurrent < 102 && yCurrent >311 && yCurrent< 376){
                    viewFlipper.setOutAnimation(this, R.anim.right_out);
                    viewFlipper.setInAnimation(this, R.anim.left_in);
                    viewFlipper.showNext();
                }else if (xCurrent < xOld || (xCurrent>661 && xCurrent < 748 && yCurrent >311 && yCurrent< 376)){
                    viewFlipper.setOutAnimation(this,R.anim.left_out);
                    viewFlipper.setInAnimation(this, R.anim.right_in);
                    viewFlipper.showPrevious();
                }
                if(xCurrent>245 && xCurrent < 528 && yCurrent >1020 && yCurrent< 1122){
                    startActivity(it);
                }
                break;
        }
        return false;
    }
}
