package com.demo01.pdkpro.bong_bay.Game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.demo01.pdkpro.bong_bay.R;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class ObjectFather {
    private float x,y;
    private int Image;
    private int sizeX,sizeY;//cai này dùng để ddingj dạng khi vẽ Bitmap
    private Bitmap ObjectModify;//trái hình trong bitmap dc dùng để chỉnh sửa
    private Bitmap ObjectDraw;//trái hình trong bitmap dc dùng để vẽ
    private float xMax, xMin = 0, yMax, yMin=0;
    private MainGame view;
    private boolean isLife = true;
    int songFile;
    SoundManager mSoundManager;
    public ObjectFather(){

    }
    public ObjectFather(int sizeX,int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    public ObjectFather(int sizeX,int sizeY,int Image, float x, float y, Context context){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.Image = Image;
        this.x = x;
        this.y = y;

        mSoundManager = new SoundManager();
        mSoundManager.initSounds(context);
        mSoundManager.addSound(0, R.raw.chamgach);
    }
    public boolean isLife() {
        return isLife;
    }

    public void setLife(boolean isLife){
        this.isLife = isLife;
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

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeX() {
        return sizeX;
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

    public void setView(MainGame view) {
        this.xMax = view.getWidth();
        this.yMax = view.getHeight();
        setBitmapFactory(view.getResources());
        setScaledBitmap(this.sizeX,this.sizeY);
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

    public MainGame getView() {
        return view;
    }

    public void setBitmapFactory(Resources resources){
        this.ObjectModify = BitmapFactory.decodeResource(resources, this.getImage());
    }

    public void setScaledBitmap(int width,int height){
        this.sizeX = width;
        this.sizeY = height;
        this.ObjectDraw = Bitmap.createScaledBitmap(this.ObjectModify, width, height, false);
    }

    public void setObjectModify(Bitmap ObjectModify) {
        ObjectModify = ObjectModify;
    }

    public Bitmap gettObjectModify() {
        return ObjectModify;
    }

    public void Draw(Canvas canvas){
        canvas.drawBitmap(this.ObjectDraw,this.x,this.y,null);
    }

}

