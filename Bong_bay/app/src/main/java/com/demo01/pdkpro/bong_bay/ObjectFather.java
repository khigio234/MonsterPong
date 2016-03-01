package com.demo01.pdkpro.bong_bay;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class ObjectFather {
    float x,y;
    int Image;
    int ballSizeX,ballSizeY;//cai này dùng để ddingj dạng khi vẽ Bitmap
    Bitmap BallModify;//trái hình trong bitmap dc dùng để chỉnh sửa
    Bitmap BallDraw;//trái hình trong bitmap dc dùng để vẽ
    float xMax, xMin = 0, yMax, yMin=0;
    View view;
    public ObjectFather(){

    }
    public ObjectFather(int ballSizeX,int ballSizeY){
        this.ballSizeX = ballSizeX;
        this.ballSizeY = ballSizeY;
    }
    public ObjectFather(int ballSizeX,int ballSizeY,int Image){
        this.ballSizeX = ballSizeX;
        this.ballSizeY = ballSizeY;
        this.Image = Image;
    }
    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public void setBallSizeY(int ballSizeY) {
        this.ballSizeY = ballSizeY;
    }

    public int getBallSizeY() {
        return ballSizeY;
    }

    public void setBallSizeX(int ballSizeX) {
        this.ballSizeX = ballSizeX;
    }

    public int getBallSizeX() {
        return ballSizeX;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getImage() {
        return Image;
    }

    public void setView(View view) {
        this.xMax = view.getWidth();
        this.yMax = view.getHeight();
        setBitmapFactory(view.getResources());
        setScaledBitmap(this.ballSizeX,ballSizeY,false);
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void setBitmapFactory(Resources resources){
        this.BallModify = BitmapFactory.decodeResource(resources, this.getImage());
    }

    public void setScaledBitmap(int width,int height,boolean fitler){
        this.ballSizeX = width;
        this.ballSizeY = height;
        this.BallDraw = Bitmap.createScaledBitmap(this.BallModify, width, height, fitler);
    }

    public void setBallModify(Bitmap ballModify) {
        BallModify = ballModify;
    }

    public Bitmap getBallModify() {
        return BallModify;
    }

    public void DrawBall(Canvas canvas){
//        Toast.makeText(this.view.getContext(),this.x+"- "+this.y,Toast.LENGTH_SHORT).show();
        canvas.drawBitmap(this.BallDraw,this.x,this.y,null);
    }

}
