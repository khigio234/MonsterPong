package com.demo01.pdkpro.bong_bay;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class ObjectFather {
    private float x,y;
    private int Image;
    private int SizeX,SizeY;//cai này dùng để ddingj dạng khi vẽ Bitmap
    private Bitmap ObjectModify;//trái hình trong bitmap dc dùng để chỉnh sửa
    private Bitmap ObjectDraw;//trái hình trong bitmap dc dùng để vẽ
    private float xMax, xMin = 0, yMax, yMin=0;
    private MainView view;
    public ObjectFather(){

    }
    public ObjectFather(int ballSizeX,int ballSizeY){
        this.SizeX = ballSizeX;
        this.SizeY = ballSizeY;
    }
    public ObjectFather(int ballSizeX,int ballSizeY,int Image){
        this.SizeX = ballSizeX;
        this.SizeY = ballSizeY;
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

    public void setSizeY(int ballSizeY) {
        this.SizeY = ballSizeY;
    }

    public int getSizeY() {
        return SizeY;
    }

    public void setSizeX(int ballSizeX) {
        this.SizeX = ballSizeX;
    }

    public int getSizeX() {
        return SizeX;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setxMax(float xMax) {
        this.xMax = xMax;
    }

    public float getxMax() {
        return xMax;
    }

    public void setxMin(float xMin) {
        this.xMin = xMin;
    }

    public float getxMin() {
        return xMin;
    }

    public float getyMin() {
        return yMin;
    }

    public void setyMin(float yMin) {
        this.yMin = yMin;
    }

    public void setyMax(float yMax) {
        this.yMax = yMax;
    }

    public float getyMax() {
        return yMax;
    }

    public int getImage() {
        return Image;
    }

    public void setView(MainView view) {
        this.xMax = view.getWidth();
        this.yMax = view.getHeight();
        setBitmapFactory(view.getResources());
        setScaledBitmap(this.SizeX,SizeY,false);
        this.view = view;
    }

    public Bitmap getObjectDraw() {
        return ObjectDraw;
    }

    public void setObjectDraw(Bitmap objectDraw) {
        ObjectDraw = objectDraw;
    }

    public Bitmap getObjectModify() {
        return ObjectModify;
    }

    public MainView getView() {
        return view;
    }

    public void setBitmapFactory(Resources resources){
        this.ObjectModify = BitmapFactory.decodeResource(resources, this.getImage());
    }

    public void setScaledBitmap(int width,int height,boolean fitler){
        this.SizeX = width;
        this.SizeY = height;
        this.ObjectDraw = Bitmap.createScaledBitmap(this.ObjectModify, width, height, fitler);
    }

    public void setObjectModify(Bitmap ObjectModify) {
        ObjectModify = ObjectModify;
    }

    public Bitmap gettObjectModify() {
        return ObjectModify;
    }

    public void Draw(Canvas canvas){
//        Toast.makeText(this.view.getContext(),this.x+"- "+this.y,Toast.LENGTH_SHORT).show();
        canvas.drawBitmap(this.ObjectDraw,this.x,this.y,null);
    }

}

