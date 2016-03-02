package com.demo01.pdkpro.bong_bay.Saved;

/**
 * Created by pdkpro on 25/02/2016.
 */
/**
 * Created by Dat on 6/1/2016.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.demo01.pdkpro.bong_bay.R;

import java.util.ArrayList;

public class BouncingBallView extends View {
    private int xMin = 0;          // This view's bounds
    private int xMax;
    private int yMin = 0;
    private int yMax;
    //the first ball
    private float ballRadius = 50; // Ball's radius
    private float ballX = ballRadius + 20;  // Ball's center (x,y)
    private float ballY = ballRadius + 200;
    private float ballSpeedX = 20 ; // Ball's speed (x,y)
    private float ballSpeedY = 18;

    private float gatBoomX = 50;
    private float gatBoomY = 0;

    private RectF ballBounds;      // Needed for Canvas.drawOval
    private Paint paint;           // The paint (e.g. style, color) used for drawing

    ArrayList<Point>   ArrBoom = new ArrayList<>();
    ArrayList<Point>   ArrRemove = new ArrayList<Point>();

    // Constructor
    public BouncingBallView(Context context) {
        super(context);
        ballBounds = new RectF();
        paint = new Paint();
    }

    // Called back to draw the view. Also called by invalidate().
    @Override
    protected void onDraw(Canvas canvas) {
        //draw screen
        this.setBackgroundResource(R.drawable.background);
        /*  Draw the ball
            ballBounds.set(xMin, yMin, xMax, yMax);
            paint.setColor(Color.GREEN);
            canvas.drawOval(ballBounds,paint);
        */
        //coppy hình ảnh bỏ vào trong drawable

        //draw ball first
        Bitmap ball = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
        Bitmap ballResize = Bitmap.createScaledBitmap(ball,50,50,false);
        canvas.drawBitmap(ballResize, ballX, ballY, null);

        //tao boom
        CreateBoom();
        //ve boom
        Bitmap bom = BitmapFactory.decodeResource(getResources(),R.drawable.boom);
        Bitmap bomSize = Bitmap.createScaledBitmap(bom,50,50,false);

        for (int j = 0;j<ArrBoom.size(); j ++){
                canvas.drawBitmap(bomSize,ArrBoom.get(j).x,ArrBoom.get(j).y,null);
        }
        //draw gat boom
        Bitmap gatBoom = BitmapFactory.decodeResource(getResources(),R.drawable.gatbong);
        Bitmap gatBoomResize = Bitmap.createScaledBitmap(gatBoom,100,40, false);
        canvas.drawBitmap(gatBoomResize,gatBoomX,gatBoomY = yMax-(yMax/5),null);
        // Update the position of the ball, including collision detection and reaction.
        update();

        // Delay
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) { }

        invalidate();  // Force a re-draw
    }

    // Detect collision and update the position of the ball.
    private void update() {
        RunBall();
    }

    private void RunBall() {
        // Get new (x,y) position
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // update lposition for the first ball

        // Detect collision and react
        if ((ballX + ballRadius > xMax)) {
            ballSpeedX = -ballSpeedX;
            ballX = xMax - ballRadius;
        } else if (ballX < xMin) {
            ballSpeedX = -ballSpeedX;
            ballX = xMin;
        }
        if (ballY + ballRadius > yMax) {
            ballSpeedY = -ballSpeedY;
            ballY = yMax - ballRadius;
        } else if (ballY < yMin) {
            ballSpeedY = -ballSpeedY;
            ballY = yMin;
        }
        //kiem tra va cham voi bom
        for (int i = 0; i < ArrBoom.size(); i++) {
            if (KhoanCach(ballX, ballY, ArrBoom.get(i).x, ArrBoom.get(i).y) < ballRadius) {
                ballSpeedY = -ballSpeedY;
                ballY = ArrBoom.get(i).y + 50;

                ArrRemove.add(ArrBoom.get(i));
                //Toast.makeText(getContext(), ArrBoom.size()+" toa do remove => " + ArrBoom.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        }
        //kiem tra va cham voi gat boom
        if((this.ballX+ballRadius >= gatBoomX && this.ballX  <= gatBoomX+100&& this.ballY+ballRadius>=gatBoomY&& this.ballY <= gatBoomY+40)){
            ballSpeedY = -ballSpeedY;
            ballY = gatBoomY - ballRadius;
            Toast.makeText(getContext(),"da va cham",Toast.LENGTH_SHORT).show();
        }
    }

    private float KhoanCach(float x1,float y1, float x2, float y2){
        float Xa = x2-x1;
        float Ya = y2 - y1;
        return (float)Math.sqrt(Xa*Xa + Ya*Ya);
    }
    //
    private void CreateBoom(){
        ArrBoom.clear();
        for (int j =0 ; j < 3; j++){
            int i = 0;
            while (i*50 < (xMax-50)){
                int x = i++*50;
                int y = j * 50;
                Point p = new Point(x,y);
                if(!ArrRemove.contains(p)){
                    ArrBoom.add(p);
                }
            }
        }
    }

    // Called back when the view is first created or its size changes.
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        xMax = w;
        yMax = h;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gatBoomX = event.getX();
        gatBoomY = event.getY();
        if(gatBoomX>=(xMax-100)){
            gatBoomX = xMax-100;
        }
        int action = event.getAction();

        return true;
    }
}