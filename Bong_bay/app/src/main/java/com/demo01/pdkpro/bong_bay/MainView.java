package com.demo01.pdkpro.bong_bay;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class MainView extends View {
    //tạo 1 trái banh
    Ball ball;
    float x =50,y=500;
    float SpeedX = 14,SpeedY = 19;
    ArrayList<Boom> ArrBoom ;
    int BoomSizeX,BoomSizeY;
    boolean cfCreateBoom = true;
    //gat bong
    Player player1,player2;
    public MainView(Context context){
        super(context);
        ball = new Ball(50,50,R.drawable.ball,SpeedX,SpeedY);
        BoomSizeX = 50;
        BoomSizeY = 50;
        ball.setX(x);
        ball.setY(y);
        ArrBoom = new ArrayList<>();
    }
    @Override
    public void onDraw(Canvas canvas){
        //set hình nền
        this.setBackgroundResource(R.drawable.background);
        //tạo ảnh cho trái banh
        ball.setView(this);
        //vẽ bóng
        ball.DrawBall(canvas);
        player1.DrawPanddle(canvas);
        player2.DrawPanddle(canvas);
        player2.update(ball);
        ball.update(player1, player2,ArrBoom);
        //ve boom
        for (Boom boom:ArrBoom) {
            boom.checkCollisionToBall(ball);//kiểm tra va chạm boom với bóng
            boom.DrawBall(canvas);
        }
        try {
            Thread.sleep(30);
        }catch (Exception e){
            e.printStackTrace();
        }
        invalidate();
    }
    public void update(){

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float gatBoomX = event.getX();
        float gatBoomY = event.getY();
        if(gatBoomX>=(this.getWidth()-100)){
            gatBoomX = this.getWidth()-100;
        }

        player1.setX(gatBoomX);

        return true;
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        //tạo boom
        if (cfCreateBoom) {
            for (int j = 3; j < 7; j++) {
                int i = 0;
                while (i * 50 < (w - 50)) {
                    int x = i++ * 50;
                    int y = 2*h/3 - j*50;
                    Boom p = new Boom(BoomSizeX, BoomSizeY, R.drawable.boom);
                    p.setX(x);
                    p.setY(y);
                    p.setView(this);
                    ArrBoom.add(p);
                }
            }
            player1 = new Player(100, 40, R.drawable.gatbong);
            player1.setX(w / 3);
            player1.setY(h - (h / 8));
            player1.setView(this);

            player2 = new Player(100, 40, R.drawable.gatbong);
            player2.setX(w - (w / 5));
            player2.setY((h / 10));
            player2.setView(this);
        }
    }
}
