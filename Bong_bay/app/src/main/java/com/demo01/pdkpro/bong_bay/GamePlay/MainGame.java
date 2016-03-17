package com.demo01.pdkpro.bong_bay.GamePlay;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.demo01.pdkpro.bong_bay.ScreenControl.GamePlayScreen;
import com.demo01.pdkpro.bong_bay.GameSaveInform.Constants;
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
    Computer computer;
    //cờ nhấn giữ
    ArrayList arrGUI = new ArrayList();
    ViewFlipper viewFlipper;
    GamePlayScreen gamePlayScreen;
    public MainGame(GamePlayScreen gamePlayScreen){
        super(gamePlayScreen.getBaseContext());
        //set hình nền
        this.setBackgroundResource(Constants.arrBackGround.get(Constants.lever));
        this.gamePlayScreen = gamePlayScreen;
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
            if(isAiWin()){
                gamePlayScreen.resultScreen();
                Constants.result = false;
            }else if(isPlayerWin()) {
                gamePlayScreen.resultScreen();
                Constants.result = true;
                } else{
                    drawAll(canvas);
                    update();
                    try {
                        Thread.sleep(30);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    invalidate();
                }
    }

    private boolean isAiWin(){
        if(ball.getY() > this.getHeight())
            return true;
        return false;
    }

    private boolean isPlayerWin(){
        if(ball.getY() < 0)
            return true;
        return false;
    }

    public void update(){
        computer.update(ball,this);
        ball.update(player, computer);
        brick.update(ball);
    }

    public void drawAll(Canvas canvas){
        ball.draw(canvas);
        player.draw(canvas);
        computer.draw(canvas);
        brick.draw(canvas);
    }

    public void init(int w,int h){
        // tao bong
        ball = new Ball(60,60,R.drawable.ball2,x,y,this.getContext(),velocityX,speedY);
        ball.setView(this);
        // tao brick
        brick = new Brick(this.getContext());
        brick.init(w,h,this);
        //tao nguoi choi
        player = new Player(180, 60, Constants.arrMonster.get(Constants.monsterPlayer), w/3, (h-h/10), this.getContext());
        player.setView(this);
        //tao may
        computer = new Computer(180, 60, Constants.arrMonster.get(Constants.lever), w/2,h/18, this.getContext());
        computer.setView(this);
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

