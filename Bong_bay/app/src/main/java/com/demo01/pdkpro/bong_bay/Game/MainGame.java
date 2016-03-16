package com.demo01.pdkpro.bong_bay.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.demo01.pdkpro.bong_bay.R;

import java.util.ArrayList;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class MainGame extends View {
    //tạo 1 trái banh
    Ball ball;
    float x =50,y=700;
    float velocityX = 10,speedY = 10;
    Brick brick;
    Player player;
    AI ai;
    //cờ nhấn giữ
    ArrayList arrGUI = new ArrayList();
    ViewFlipper viewFlipper;

    public MainGame(Context context){
        super(context);
        //set hình nền
        this.setBackgroundResource(R.drawable.map3);
    }

    public void setArrGUI(ArrayList arrGUI) {
        this.arrGUI = arrGUI;
    }

    public void setViewFlipper(ViewFlipper viewFlipper) {
        this.viewFlipper = viewFlipper;
    }
    public void rePlay(){
        if(this.ball!=null){
            this.ball.setLife(true);
        }
    }
    @Override
    public void onDraw(Canvas canvas){
            drawAll(canvas);
            update();
            try {
                Thread.sleep(30);
            }catch (Exception e){
                e.printStackTrace();
            }
            invalidate();

    }
    public void update(){
        ai.update(ball,this);
        ball.update(player, ai);
        brick.update(ball);
    }

    public void drawAll(Canvas canvas){
        ball.draw(canvas);
        player.draw(canvas);
        ai.draw(canvas);
        brick.draw(canvas);
    }

    public void init(int w,int h){
        // tao bong
        ball = new Ball(50,50,R.drawable.ball2,x,y,this.getContext(),velocityX,speedY);
        ball.setView(this);
        // tao brick
        brick = new Brick(this.getContext());
        brick.init(w,h,this);
        //tao nguoi choi
        player = new Player(100, 40, R.drawable.pink, w/3, (h-h/8), this.getContext());
        player.setView(this);
        //tao may
        ai = new AI(100, 40, R.drawable.star, w/2,(h/10), this.getContext());
        ai.setView(this);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        player.update(event,this);
        return true;
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
            init(w,h);
    }
}

