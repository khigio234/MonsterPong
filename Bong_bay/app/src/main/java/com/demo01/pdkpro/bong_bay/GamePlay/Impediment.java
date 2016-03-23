package com.demo01.pdkpro.bong_bay.GamePlay;

import android.content.Context;
import android.graphics.Canvas;

import com.demo01.pdkpro.bong_bay.R;

/**
 * Created by CO on 3/22/2016.
 */
public class Impediment {
     Components components_1;
     Components components_2;
     int radius;
     float omega;
     int t = 0, k=1;

    public Impediment(Context context, MainGame view){
        radius = 200;
        omega = 0.1f;
        components_1 = new Components(70,70, R.drawable.impediment1, 384, 440, context);
        components_1.setView(view);
        components_2 = new Components(70,70, R.drawable.impediment2, 384, 840, context);
        components_2.setView(view);
    }

    public void update(Ball ball){
        t++;
        //if(t > 72) t=0;
        float x = (float)(radius*Math.cos(omega * t));
        float y = (float)(radius*Math.sin(omega * t));
        components_1.setX(374+x);
        components_1.setY(600+y);

        components_2.setX(374-x);
        components_2.setY(600 - y);

        processingCollision(ball,components_1);
        processingCollision(ball, components_2);
    }

    public void draw(Canvas canvas) {
        components_1.draw(canvas);
        components_2.draw(canvas);

    }

    public void processingCollision(Ball ball, Components com) {
        float tempX = ball.getX()+ball.getSizeX()/2;
        float tempYBottom = ball.getY() + ball.getSizeY();

        if(isCollison(tempX,ball.getY(),com.getX(),com.getY(),com.getSizeX()))
            setVelocityBall(ball,-1f,-1f);
        if(isCollison(tempX,tempYBottom,com.getX(),com.getY(),com.getSizeX())){
            setVelocityBall(ball,1f,-1f);
        }
        else {

            float tempY = ball.getY() + ball.getSizeY() / 2;
            float tempXLeft = ball.getX() + ball.getSizeX();
            if (isCollison(ball.getX(), tempY, com.getX(), com.getY(), com.getSizeY()))
                setVelocityBall(ball,1f,1f);
            if(isCollison(tempXLeft, tempY, com.getX(), com.getY(), com.getSizeY())) {
                setVelocityBall(ball,-1f,1f);
            }
        }
    }

    private boolean isCollison(float ax, float ay, float bx, float by, float bs){
        return bx < ax && ax < bx+bs && by < ay && ay < by+bs;
    }

    private void setVelocityBall(Ball ball, float flagNavigationX, float flagNavigationY){
        float x = flagNavigationX * ball.getSpeed();
        float y = flagNavigationY * 10*k;
        k++;
        ball.setvelocityX(x);
        ball.setvelocityY(y);
    }
}
