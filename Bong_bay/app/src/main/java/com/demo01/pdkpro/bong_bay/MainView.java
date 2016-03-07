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
    float x =50,y=700;
    float SpeedX = 10,SpeedY = 10;
    ArrayList<Boom> ArrBoom ;
    int BoomSizeX,BoomSizeY;
    boolean cfCreateBoom = true;
    //gat bong
    Player player1,player2;
    //cờ nhấn giữ
    Boolean cfMove = false;

    public MainView(Context context){
        super(context);
        ArrBoom = new ArrayList<>();
    }
    @Override
    public void onDraw(Canvas canvas){
        //set hình nền
        this.setBackgroundResource(R.drawable.map2);
        //vẽ bóng
        ball.Draw(canvas);
        player1.Draw(canvas);
        player2.Draw(canvas);
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
        float touchX= event.getX();
        float touchY = event.getY();
        //touchX> player1.getX() && touchX< player1.getX() + player1.getSizeX()
        //&& touchY > player1.getY() && touchY < player1.getY() + player1.getSizeY() && event.getAction() ==
        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            this.cfMove = true;
        }
        //bat su kiện move
        if(cfMove){
            if (touchX >= this.getWidth() - player1.getSizeX()) {
                touchX = this.getWidth() - player1.getSizeX()/2;
            }
            player1.setX(touchX-player1.getSizeX()/2 < 0 ? 0:touchX-player1.getSizeX()/2);
        }
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(player1.getX() < touchX)
                player1.setX(player1.getX() + 20 > this.getWidth() ? this.getWidth() - player1.getSizeX(): player1.getX() + 20);
            if(player1.getX() > touchX)
                player1.setX(player1.getX() - 20<0? 0:player1.getX()-20);
        }
        //neu thả ra thì hết move
        if(event.getAction() == MotionEvent.ACTION_UP){
            this.cfMove = false;
        }
        return true;
    }


    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        //khoi tao
        if (cfCreateBoom) {
            //tao banh
            ball = new Ball(50,50,R.drawable.ball1,SpeedX,SpeedY);
            BoomSizeX = 50;
            BoomSizeY = 50;
            ball.setX(x);
            ball.setY(y);
            ball.setView(this);
            ball.setSong(this.getContext());
            //tao boom
            for (int j = 3; j < 7; j++) {
                int i = 0;
                while (i * 50 < (w - 50)) {
                    int x = i++ * 50;
                    int y = 2*h/3 - j*50;
                    Boom p = new Boom(BoomSizeX, BoomSizeY, R.drawable.block1);
                    p.setX(x);
                    p.setY(y);
                    p.setView(this);
                    p.setSong(this.getContext());
                    ArrBoom.add(p);
                }
            }
            //tao nguoi choi
            player1 = new Player(100, 40, R.drawable.pink);
            player1.setX(w / 3);
            player1.setY(h - (h / 8));
            player1.setView(this);
            player1.setSong(this.getContext());
            //tao may choi
            player2 = new Player(100, 40, R.drawable.star);
            player2.setX(w - (w / 5));
            player2.setY((h / 10));
            player2.setView(this);
            player2.setSong(this.getContext());
        }
    }

}