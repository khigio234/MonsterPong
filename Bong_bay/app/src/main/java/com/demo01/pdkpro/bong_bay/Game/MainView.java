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
    ArrayList arrGUI = new ArrayList();
    ViewFlipper viewFlipper;

    public MainView(Context context,ArrayList arrGUI){
        super(context);
        ArrBoom = new ArrayList<>();
        this.arrGUI = arrGUI;
        //set hình nền
        this.setBackgroundResource((Integer) arrGUI.get(0));
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
        if(this.ball.isLife()){
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
        }else {
            this.setBackgroundResource(R.drawable.menu00);
            this.viewFlipper.setDisplayedChild(0);
        }
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
            if (touchX + player1.getSizeX()/2 >= this.getWidth()) {
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
            this.cfCreateBoom = false;
            //tao banh
            ball = new Ball(50,50,(Integer)this.arrGUI.get(1),SpeedX,SpeedY);
            BoomSizeX = 50;
            BoomSizeY = 50;
            ball.setX(x);
            ball.setY(y);
            ball.setView(this);
            ball.setSong(this.getContext());
            //tao boom
            for (int j = 3; j < 5; j++) {
                int i = 3;
                while ((i+3) * 50 < (w - 50)) {
                    int x = i++ * 50;
                    int y = 2*h/3 - j*50;
                    Boom p = new Boom(BoomSizeX, BoomSizeY,(Integer)this.arrGUI.get(2));
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
            player2.setX(w/2 - player2.getX());
            player2.setY((h / 10));
            player2.setView(this);
            player2.setSong(this.getContext());
        }
    }

}